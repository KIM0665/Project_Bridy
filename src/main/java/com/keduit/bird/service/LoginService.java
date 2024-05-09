package com.keduit.bird.service;


import com.keduit.bird.constant.Role;
import com.keduit.bird.dto.MemberFormDTO;
import com.keduit.bird.entity.Member;
import com.keduit.bird.entity.Profile;
import com.keduit.bird.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
@Transactional
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {
//    UserDetailsService = 만들어져 있는 가입보안 기능 클래스

    private final MemberRepository memberRepository;
    private final CertCodeService certCodeService;
    private final ProfileService profileService;


    //회원 등록
    public String saveMember(Member member, MultipartFile profileFile) throws Exception{
        validateDuplicateMember(member);
        memberRepository.save(member);

        Profile profile = new Profile(member);
        profile.setMember(member);

        profileService.saveProfile(profile, profileFile);

        return member.getMemberEmail();
    }


    // 회원가입 시 회원 중복 확인
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByMemberEmail(member.getMemberEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    // 로그인시 회원 확인
    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberEmail(memberEmail);

        if(member == null){
            throw new UsernameNotFoundException("존재하지 않는 회원입니다.");
            //이메일을 찾아오는 데 값이 없다면, 인셉션 발생
        }
        if(member.getRole() == Role.STOP){
            throw new IllegalStateException("탈퇴된 회원입니다.");
        }

        return User.builder()
                .username(member.getMemberEmail())
                .password(member.getMemberPwd()).roles(member.getRole().toString()).build();
//        User = UserDetailsService 에서 생성되는 키워드.
//        빌더를 사용해 필요한 것들을 호출하고, 빌드 완료.
    }

    //이메일 인증코드 확인하기
    public void verifyEmail(String memberEmail, String certCode) {
        String storedCode = certCodeService.getCertCode(memberEmail);
        System.out.println("이메일 ----> " + memberEmail + "코드------> " + certCode + "발송코드------> " + storedCode);
        if (storedCode != null && storedCode.equals(certCode)) {
            Member member = new Member();
            certCodeService.deleteCertCode(memberEmail);
            // 인증에 사용된 코드 삭제
            System.out.println("이메일 인증 성공!");
        }else{
            System.out.println("인증 코드가 일치하지 않습니다.");
        }
    }

    //비밀번호 수정하기
    // 비밀번호 재설정을 위한 회원 확인
    public boolean memberEmailCheck(String memberEmail) {
        Member findMember = memberRepository.findByMemberEmail(memberEmail);
        return findMember != null;
    }

    public void updatePassword(String memberPwd, String memberEmail) throws Exception{
        memberRepository.updatePassword(memberPwd, memberEmail);
    }

}
