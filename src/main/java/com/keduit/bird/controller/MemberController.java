package com.keduit.bird.controller;

import com.keduit.bird.dto.MemberFormDTO;
import com.keduit.bird.entity.Member;
import com.keduit.bird.service.CertCodeService;
import com.keduit.bird.service.EmailService;
import com.keduit.bird.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final LoginService loginService;
    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;
    private final CertCodeService certCodeService;

    @GetMapping("/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDTO", new MemberFormDTO());
        return "member/memberForm";
    }

    @PostMapping("/new")
    public String newMember(@Valid MemberFormDTO memberFormDTO,
                            BindingResult bindingResult, Model model, MultipartFile profileFile){
        if(bindingResult.hasErrors()){
            System.out.println("-----가입 중 오류 발생-----");
            System.out.println("데이터 확인 : " + memberFormDTO);
            return "member/memberForm";
        }

        try {
            Member member = Member.createMember(memberFormDTO, passwordEncoder);
            loginService.saveMember(member, profileFile);
            System.out.println("저장 완료!");
        }catch(IllegalStateException e){
            model.addAttribute("errorMessage", "이미 가입된 회원입니다.");
            return "member/memberForm";
        }catch(Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            System.out.println("회원 데이터 저장 중 오류 발생");
            return "member/memberForm";
        }
        return "redirect:/"; // 로그인 성공 후 홈페이지로 리디렉션
    }

    //이메일 인증
    @PostMapping("/cert")
    public ResponseEntity<String> certCode(@RequestBody Map<String, String> requestData){
        String memberEmail = requestData.get("memberEmail");
        if(memberEmail !=null) {
            String certCode = emailService.generateCode();
            System.out.println("------이메일 전달 확인 : " + memberEmail);
            System.out.println("------코드 확인 : " + certCode);
            emailService.sendMail(memberEmail, certCode);
            certCodeService.saveCertCode(memberEmail, certCode);
            System.out.println(memberEmail + "###" + certCode);

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().body("이메일 주소를 찾을 수 없습니다.");
        }
    }
    @PostMapping("/verify")
    public ResponseEntity<String> verifyCode(@RequestBody Map<String, String> requestData) {
        String memberEmail = requestData.get("memberEmail");
        System.out.println("--------" + memberEmail);
        String certCode = requestData.get("certCode");
        System.out.println("--------" + certCode);
        try {
            loginService.verifyEmail(memberEmail, certCode);
            System.out.println("인증코드 비교 : " + memberEmail + "/" + certCode);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //로그인
    @GetMapping("/login")
    public String loginForm(){
        System.out.println("홈으로 이동");
        return "member/memberLoginForm";
    }

    @GetMapping("/about")
    public String aboutForm(){
        return "member/about";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        System.out.println("에러 컨트로러러======");
        model.addAttribute("loginErrorMsg", "이메일, 혹은 비밀번호를 확인해주세요.");
        return "/member/memberLoginForm";
    }

    // 비밀번호 분실시 재설정하기
    @GetMapping("/changePassword")
    public String changePass(Model model){
        model.addAttribute("memberFormDTO", new MemberFormDTO());
        return "member/changePassForm";
    }
    //회원 확인을 위한 이메일 존재 확인
    @PostMapping("/checkEmail")
    public ResponseEntity<Boolean> memberCheck(String memberEmail){
        boolean exists = loginService.memberEmailCheck(memberEmail);
        return ResponseEntity.ok(exists);
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
            return "members/changePassForm";
        }
        System.out.println("성공!!");
        return "redirect:/";
    }


}
