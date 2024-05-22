# <img src="https://github.com/koyuhjkl123/portfolio/assets/94844952/4956f6c6-f49a-4f9b-a7c0-95ce6e4f72c0" width="40" height="40"/> 지식과 정보를 공유하는 조류 포털 Birdy! <br>

 자연과 조화를 이루며 공존하는 새들에 대한 흥미와 참여를 넓히고 새들이 우리 주변에서 <br>
 어떻게 존재하는지를 더 깊이 이해하며 사람들간의 조류에 대해 지식을 공유하는 사이트  <br>
 <br>

# 목차
- 개요
- 프로젝트 구성
- 팀원 및 업무 분담
-  BIRDY핵심기능 설명
-  BIRDY코드
-  시연 연상
-  팀원 후기
<br>

# 개요
* 프로젝트 이름 : Birdy!
* 프로젝트 목적 : 사용자가 저희 사이트를 통해 다양한 지식을 소통을 통해 공유하면서 탐조(새를 관찰)를 하기 위한 사이트
* 프로젝트 개발 기간 : 2024.04 ~ 2024.05
* 프로젝트 구성 인원 : 3명
* 프로젝트 개발 환경
  * 언어 : Java 11, HTML/CSS, JavaScript, Python
  * 프레임워크 : Spring Framework, JPA, Spring Security
  * DB : MySQL
  * API 라이브러리 : Jquery, Chart, Gsap, Bootstrap
<br>

# 프로젝트 구성
* 메인 페이지
* 새 앨범
* 커뮤니티 게시판
* 새 뉴스
* 새의 탐조
* 회원가입/로그인
 <br>
 
# 팀원 업무 분담 내역
팀장으로써의 역할을 맡았고 프로젝트 관리 및 일정 조율을 하였습니다. <br>
전반적인 프로젝트의 구성 및 ERD 설계 등 초안을 통해 팀원과 회의를 하였고 시행 착오를 겪으면서 구성하였습니다 <br>
<br>

## <img src="https://github.com/koyuhjkl123/Project_Bridy/assets/94844952/fdf8fb32-8c9f-4859-a343-429eef2b0dd2" width="40" height="40"/>김진수(팀장)
* 담당 역할
  * 전체 페이지 통합 및 구성
    * 카테고리별 메인 페이지 재가공
    * 메인 페이지에 구성에 필요한 카테고리별 페이징 처리
  * ERD, 웹 사이트 기능 설계
  * 새의 통계 및 그래프 시각화
  * 인트로(애니메이션) 기능 구현
  * 새의 뉴스 페이지(일반, 과학) 기능 구현
  * 부트스트랩으로 활용한 화면 구성
  * 관리자 페이지 기능 구현
    * 새의 종류 별 개체 수
    * 멸종 위기 종(등급) 별 개체 수
    * 회원 관리(조회, 권한(회원 탈퇴 시 정지), 수정)

## <img src="https://github.com/koyuhjkl123/Project_Bridy/assets/94844952/acef5d1e-db23-4245-8ad3-89ce729a4057" width="40" height="40"/>이세진(팀원)
* 담당 역할
  * 회원가입 및 로그인
    * 아이디, 이메일 중복 검사
    * 비빌번호 재확인 검사
    * 이메일 이용한 본인 인증
    * 로그아웃 기능
    * 소셜 로그인(카카오톡)
  * 마이페이지
    * 회원정보 불러오기
    * 개인 정보 수정(비밀번호)
    * 닉네임 수정 시 중복 확인
    * 프로필 사진 업로드 기능
    * 정보 변경 시 업데이트 기능
  * About US
    * 사이트 소개 및 팀원 소개
  * 새의 앨범
    * **"ㄱ, ㄴ, ㄷ"** 모음에 따른 데이터 분류
    * 멸종 위기종 **1급, 2급** 별 분류
    * 새의 사진과 이름, 간략한 설명글 구성

  ## <img src="https://github.com/koyuhjkl123/Project_Bridy/assets/94844952/7418d06b-b9aa-41ce-8c4a-d551302da9b6" width="40" height="40"/>김태리(팀원)
  * 담당 역할
    * 커뮤니티 게시판
      * 게시글, 댓글 CRUD 구현
      * 게시판 파일 업로드
      * 목록 페이징 처리
    * 탐조대 구현
      * 카카오 지도 API를 활용한 탐조대 시각화 구현
      * 철새의 기본 정보 안내
      
# Birdy 핵심 기능 설명
저희 사이트 핵심 코드는 "**카카오 소셜로그인, 카카오API를 활용한 지도 구현, 커뮤니티 게시판, 새의 통계 그래프 시각화, 새의 앨범**" <br>
사용자가 저희 사이트를 통해 다양한 지식을 소통을 통해 공유하면서 탐조(새를 관찰)를 하기 위한 사이트

## 카카오 소셜 로그인
회원가입 양식을 제공하고 새 회원을 등록하고 저장, 이메일 인증처리와 로그인 양식 비밀번호 분실시 변경 양식 제공, 이메일 중복 체크.
Thymeleaf 템플릿 엔진을 사용하여 로그인 페이지를 생성하고, 카카오 로그인 링크와 함께 소셜 로그인 옵션 제공.
코드 보기(html)
```html
<!--로그인하기-->
<div layout:fragment="content">
    <form action="/members/login" role="form" method="post">

        <div class="form-group">
            <label th:for="memberEmail">Email</label>
            <input type="email" class="form-control"
                   name="memberEmail" placeholder="이메일을 입력해주세요">
        </div>

        <div class="form-group">
            <label th:for="memberPwd">Password</label>
            <input type="password" id="memberPwd" class="form-control"
                   name="password" placeholder="비밀번호를 입력해주세요">
        </div>

        <!--카카오 로그인-->
        <div class="input-group md-3">
            <a href="/oauth2/authorization/kakao">kakao login</a>
        </div>

        <div>
        <p th:if="${loginErrorMsg}"
           th:text="${loginErrorMsg}" class="error">Error Message!</p>
        <button class="btn btn-primary">로그인</button>
        <button type="button" class="btn btn-primary"
                onclick="location.href='/members/new'">회원가입</button>
        </div>

        <a href="/members/changePassword" rel="external">비밀번호 분실</a>

<!--        <input type="hidden" th:name="${_csrf.parameterName}"-->
<!--               th:value="${_csrf.token}">-->
    </form>
```
![MemberController.java](C:\myBoot\Bird\src\main\java\com\keduit\bird\controller)

코드보기(Controller)
```java
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

    //어바웃 어스 페이지 이동
    @GetMapping("/about")
    public String aboutForm(){
        return "member/about";
    }


}

```
