package com.keduit.bird.service;

import com.keduit.bird.entity.Profile;
import com.keduit.bird.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityExistsException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {

    @Value("${profileLocation}")
    private String profileLocation;
    //애플리케이션에 설정해둔 경로를 가져옴

    private final ProfileRepository profileRepository;
    private final FileService fileService;

    public void saveProfile(Profile profile, MultipartFile multipartFile)
        throws Exception{

        String profileOrigin = multipartFile.getOriginalFilename();
        String profileName = "";
        String profileUrl = "";

        if(!StringUtils.isEmpty(profileOrigin)){
            profileName = fileService.uploadFile(profileLocation, profileOrigin, multipartFile.getBytes());
            profileUrl = "/pj/members/" + profileName;
        }
        profile.updateProfile(profileOrigin, profileName, profileUrl);
        profileRepository.save(profile);
    }

    //프로필 이미지 교체하기(기존 이미지 삭제 후 교체)
    public void updateProfile(Long profileId, MultipartFile profileFile)
        throws Exception{
        if(!profileFile.isEmpty()){
            Profile savedProfile = profileRepository.findById(profileId)
                    .orElseThrow(EntityExistsException::new);

            if(!StringUtils.isEmpty(savedProfile.getProfileName())){
                fileService.deleteFile(profileLocation +
                        "/" + savedProfile.getProfileName());
            }

            String profileOrigin = profileFile.getOriginalFilename();
            String profileName = fileService.uploadFile(profileLocation, profileOrigin,profileFile.getBytes());
            String profileUrl = "/pj/members/" + profileName;

            savedProfile.updateProfile(profileOrigin, profileName, profileUrl);
        }
    }

    // 프로필 이미지를 가져오는 메서드
    public byte[] getProfileImage(String memberEmail) {
        Profile profile = profileRepository.findByMemberMemberEmail(memberEmail);
        // 멤버의 이메일로 join 된 이미지를 찾아옴.

        if (profile != null) {
            String profileUrl = profile.getProfileUrl();
            try {
                // 프로필 이미지 파일을 읽어 byte 배열로 반환합니다.
                return Files.readAllBytes(Paths.get(profileUrl));
            } catch (IOException e) {
                // 파일 읽기 중 오류가 발생한 경우 null을 반환합니다.
                e.printStackTrace();
            }
        }
        // 프로필 이미지가 없다면 null 반환
        return null;
    }
}
