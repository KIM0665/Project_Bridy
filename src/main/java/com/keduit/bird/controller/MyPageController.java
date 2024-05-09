package com.keduit.bird.controller;

import com.keduit.bird.dto.MemberFormDTO;
import com.keduit.bird.entity.Member;
import com.keduit.bird.entity.Profile;
import com.keduit.bird.repository.MemberRepository;
import com.keduit.bird.repository.ProfileRepository;
import com.keduit.bird.service.LoginService;
import com.keduit.bird.service.MemberService;
import com.keduit.bird.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;

@Controller
@RequestMapping("/myPage")
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final ProfileService profileService;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoginService loginService;

    //마이페이지에 회원 정보 띄우기
    @GetMapping("/myPage_main")
    public String myPageDtl(Principal principal, Model model){
        //principal : 현재 접속해있는 사용자의 정보를 불러옴.
        try{
            MemberFormDTO memberFormDTO = memberService.getMemberDtl(principal.getName());
            model.addAttribute("member", memberFormDTO);
        }catch(EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 회원입니다.");
            model.addAttribute("member", new MemberFormDTO());
            return "login/memberLoginForm";
            //memberFormDTO 에서 가져오는 객체들을 member의 변수명으로 호출함
        }
        return "myPage/myPageForm";
    }

    //회원탈퇴 화면이동
    @GetMapping("/memberStop")
    public String memberStopForm(Principal principal, Model model){
        try{
            MemberFormDTO memberFormDTO = memberService.getMemberDtl(principal.getName());
            model.addAttribute("member", memberFormDTO);
        }catch(EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 회원입니다.");
            model.addAttribute("member", new MemberFormDTO());
            return "login/memberLoginForm";
        }
        return "member/memberStopForm";
    }

    //회원탈퇴 처리 = 권한 STOP
    @PostMapping("/memberStop")
    public String memberStop(Principal principal,
                             RedirectAttributes redirectAttributes){
        String memberEmail = principal.getName();
        Member member = memberRepository.findByMemberEmail(memberEmail);

        if(memberEmail != null){
            // 회원의 id를 조회하여 해당 회원의 권한 변경(service)
           memberService.memberStop(member);
            SecurityContextHolder.clearContext();
            redirectAttributes.addFlashAttribute("message", "회원 탈퇴가 완료되었습니다.");
        }
        //탈퇴 성공시 메인 화면으로 이동
        return "redirect:/";
    }

    //회원 정보 수정
    @GetMapping("/update")
    public String memberDtl(Principal principal, Model model){
        try{
            MemberFormDTO memberFormDTO = memberService.getMemberDtl(principal.getName());
            model.addAttribute("memberFormDTO", memberFormDTO);
        }catch(EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 회원입니다.");
            model.addAttribute("member", new MemberFormDTO());
            return "login/memberLoginForm";
            //memberFormDTO 에서 가져오는 객체들을 member의 변수명으로 호출함
        }
        return "myPage/updateForm";
    }

    @PostMapping("/update")
    public String memberUpdate(@RequestParam String memberName,
                               @RequestParam String memberPhone,
                               @RequestParam String memberEmail,
                               Principal principal,
                               @RequestParam("profileFile") MultipartFile profileFile,
                               Model model){
        try{
            // 프로필 이미지 업데이트
            if (!profileFile.isEmpty()) {
                Profile profile = profileRepository.findByMemberMemberEmail(memberEmail);
                profileService.updateProfile(profile.getId(), profileFile);
            }

            memberService.updateMemberInfo(memberName, memberPhone, memberEmail);
            System.out.println("변경사항 업데이트중.....");

            return myPageDtl(principal, model);
        }catch(Exception e){
            model.addAttribute("errorMessage", "업데이트 과정 중 오류가 발생했습니다.");
            return "myPage/updateForm";
        }
    }

    @GetMapping("/changePassword")
    public String showChangePasswordForm(Model model, Principal principal) {
        try{
            MemberFormDTO memberFormDTO = memberService.getMemberDtl(principal.getName());
            model.addAttribute("memberFormDTO", memberFormDTO);
        }catch(EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 회원입니다.");
            model.addAttribute("member", new MemberFormDTO());
            return "login/memberLoginForm";
            //memberFormDTO 에서 가져오는 객체들을 member의 변수명으로 호출함
        }
        return "myPage/changePwd";
    }

    //해당 이메일 회원의 비밀번호 업데이트
    @PostMapping("/changePassword")
    public String pwdUpdate(@RequestParam String memberEmail,
                            @RequestParam String memberPwd,
                            Model model){
        try{
            String newPwd = Member.updatePwd(memberPwd, passwordEncoder);
            loginService.updatePassword(newPwd, memberEmail);
        }catch (Exception e){
            model.addAttribute("errorMessage", "비밀번호 변경 중 오류발생!");
            return "myPage/changePwd";
        }
        System.out.println("성공!!");
        return "redirect:/";
    }

    @GetMapping("/profile/{memberEmail}")
    @ResponseBody
    public ResponseEntity<Resource> getProfileImage(@PathVariable String memberEmail) {
        // memberEmail을 이용하여 프로필 이미지 파일을 불러옵니다.
        byte[] profileImage = profileService.getProfileImage(memberEmail);

        // 프로필 이미지가 존재하지 않을 경우 기본 이미지를 반환합니다.
        if (profileImage == null) {
            Resource defaultImage = new ClassPathResource("/static/images/default_profile_image.jpg");
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(defaultImage);
        }

    // 프로필 이미지를 반환합니다.
    ByteArrayResource resource = new ByteArrayResource(profileImage);
    return ResponseEntity.ok()
            .contentType(MediaType.IMAGE_JPEG)
            .body(resource);
}

}
