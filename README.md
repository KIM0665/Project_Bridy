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
저희 사이트 핵심 코드는 "**회원가입/로그인, 카카오API를 활용한 지도 구현, 커뮤니티 게시판, 새의 통계 그래프 시각화, 새의 앨범**" <br>
사용자가 저희 사이트를 통해 다양한 지식을 소통을 통해 공유하면서 탐조(새를 관찰)를 하기 위한 사이트

## 회원가입/로그인
회원가입 양식을 제공하고 새 회원을 등록하고 저장, 이메일 인증처리와 로그인 양식 비밀번호 분실시 변경 양식 제공, 이메일 중복 체크.
Thymeleaf 템플릿 엔진을 사용하여 로그인 페이지를 생성하고, 카카오 로그인 링크와 함께 소셜 로그인 옵션 제공.
<br>

<details>
    <summary>코드 보기(html)</summary>

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

</details>


<details>
    <summary>코드 보기(Controller)</summary>

```java

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

</details>

<details>
    <summary>코드 보기(Service)</summary>

```java

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //마이페이지에 사용자 정보 가져오기(읽기 전용)
    @Transactional(readOnly = true)
    public MemberFormDTO getMemberDtl(String memberEmail) {
        Member member = memberRepository.findByMemberEmail(memberEmail);
        MemberFormDTO memberFormDTO = MemberFormDTO.of(member);
        return memberFormDTO;
    }

    //회원 권한 박탈시키기(STOP: 부여된 권한 없음 = all 허용 가능 외 접근 불가.)
    public void memberStop(Member member){
        if(member != null){
            // id로 회원을 조회해 권한 변경(stop)
            member.setRole(STOP);
            memberRepository.save(member);
        }
    }
    //회원 정보 수정
    public void updateMemberInfo(String memberName, String memberPhone, String memberEmail) throws Exception{
        memberRepository.updateMemberInfo(memberName, memberPhone, memberEmail);
    }

    // 모든 회원 조회
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // ID로 회원 조회
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 회원이 존재하지 않습니다: " + id));
    }

    // 회원 정보 업데이트
    public void updateMember(Long id, MemberFormDTO memberFormDTO) {
        Member member = getMemberById(id);
        // MemberFormDTO를 사용하여 업데이트 로직 구현
        // 예: member.setEmail(memberFormDTO.getEmail());
        //    member.setName(memberFormDTO.getName());
        memberRepository.save(member);
    }

    // 회원 삭제(완전삭제)
    public void deleteMember(Long id) {
        Member member = getMemberById(id);
        memberRepository.delete(member);
    }

    public List<Member> searchMembers(String term) {
        // 회원 이름 또는 이메일에 검색어가 포함된 회원을 찾아서 반환
        return memberRepository.findByMemberNameContainingIgnoreCaseOrMemberEmailContainingIgnoreCase(term, term);
    }

    //유저 네임 가져오기
    public String findMemberNameByMemberEmail(String memberEmail) {
        return memberRepository.findMemberNameByMemberEmail(memberEmail);
    }

}

```
</details>

## 카카오 API지도를 활용한 지도 구현
카카오 API를 활용한 지도 위치 서비스 활용 전국 추천 철새 탐조대 구현,
마커 라이브러리 활용 탐조대 마커 표기

<details>
    <summary><i>코드 보기(HTML)</i></summary>
	
```html
	
<!DOCTYPE html>
<html lang="ko" xmlns:th="https://thymeleaf.org"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout"
      layout:decorate='~{layouts/layout1}'>

    <title>탐조</title>
    <th:block layout:fragment="css">
    <style>
        /* 기본 CSS 설정 */
       body, h1, p {
           margin: 0;
           padding: 0;
       }
       .main-box {
           border: 2px solid black;
           padding: 20px;
           margin: 20px;
       }
       .section-box {
           border: 1px solid grey;
           margin: 10px;
           padding: 10px;
           background-color: #f9f9f9;
       }

       /* 헤더 스타일 */
        header {
            background-color: #f8f9fa;
            padding: 20px;
            text-align: center;
            font-size: 24px;
            color: #333;
        }
         /* 버튼 스타일 */
        .bird-button {
            margin: 10px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }
        .footer{
            margin-top: 50px;
        }
         .map-container {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh; /* 전체 뷰포트 높이를 사용 */
    }

    </style>
    </th:block>

    <th:block layout:fragment="script">
        <script th:inline="javascript">
            window.onload = function(){

            var container = document.getElementById('map');
            var options = {
                center: new kakao.maps.LatLng(36.687009,128.046518),
                level: 13
            };
            var map = new kakao.maps.Map(container, options);
		// </맵 생성>

            var positions = [
                {
                    title: '장항습지탐조대',
                    latlng: new kakao.maps.LatLng(37.641278, 126.758219)
                },
                {
                    title: '갯골생태공원탐조대',
                    latlng: new kakao.maps.LatLng(37.392934, 126.776943)
                },
                {
                    title: '명지철새탐조대',
                    latlng: new kakao.maps.LatLng(35.083967, 126.911815)
                },
                {
                    title: '주남저수지탐조대',
                    latlng: new kakao.maps.LatLng(35.310929, 128.680185)
                },
                {
                    title: '간월호철새탐조대',
                    latlng: new kakao.maps.LatLng(36.601085,126.440018)
                },
                {
                    title: '하도철새탐조대',
                    latlng: new kakao.maps.LatLng(33.512076,126.896637)
                },
                {
                    title: '탑립돌보탐조대',
                    latlng: new kakao.maps.LatLng(36.394635, 127.409965)
                }

            ];

            for (var i = 0; i < positions.length; i ++) {

                var marker = new kakao.maps.Marker({  // 마커 생성
                    map: map, // 마커를 표시할 지도
                    position: positions[i].latlng, // 마커를 표시할 위치
                    title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시 됨
                });
            }
            }
        </script>
    </th:block>
    <div layout:fragment="content">
        <header>
            <h4> 철새와 탐조  </h4>
        </header>

        <div class="container">
        <div class="main-box">
            <div class="section-box">
                <h1>철새란 무엇인가요?</h1>
                <p>4계절이 있는 우리나라에서는 계절에 따라 다양한 새가 관찰됩니다. 겨울에 따라 이동을 하지 않는 새를 도시새라 하고, 계절에 따라 번식지와 월동지를 이동하는 새를 철새라고 합니다. 철새에는 여름철새, 겨울철새, 동반철새가 있으며, 이외에 길을 잃은 새가 조금합니다.</p>
            </div>
            <div class="section-box">
                <h2>철새와 도시새는 어떻게 구분되나요?</h2>
                <p>철새와 도시새는 번식지와 월동지로 다 그종 이나는 부분에서 차이가 나고 도시화하는 과정에서 발생한 다양한 생활환경에 따라 적응력에 차이가 따라 분류됩니다.</p>
                <p>철새는 사질의 기호와 매이 높고 도시화로부터 상대적으로 먼 지역에서 번식하고, 일부는 양질의 먹이를 찾아 먼 거리를 이동합니다. 반면, 도시새는 인간 주변에서 활동하는 대표적인 새로서 일부는 철새로 변할 수 있습니다.</p>
            </div>
            <div class="section-box">
                <h2>흔히 관찰되는 철새는 무엇인가요?</h2>
                <p>흔히 관찰되는 철새로는 많은 수가 겨울을 채우고 봄에 되면 북상하는 대표적인 겨울철새입니다, 일부는 양질 먹이를 찾아서 거리를 이동합니다. 철새의 양상이나 부류가 다양한 세상에서의 종에서 유형에 따라 분류됩니다.</p>
            </div>
        <h2>전국 추천 탐조대</h2>
        </div>
        <div id="map" style="width: 600px; height: 450px; margin: 0 0 0 20px;"></div>
        <!-- 컨테이너를 추가하여 지도와 목록을 나란히 배치 -->
        <div style="width: 100%; overflow: hidden;">
            <!-- 목록 박스 -->
            <div style="float: right; margin-left: 20px; padding: 20px;
            border: 1px solid #ccc; width: 635px; height: 450px; position: absolute;
            top: 43.8%;
            left: 800px;">
                <h3>탐조대 목록</h3>
                <ol>
                    <li>장항습지탐조대</li><br>
                    <li>갯골생태공원탐조대</li><br>
                    <li>명지철새탐조대</li><br>
                    <li>주남저수지탐조대</li><br>
                    <li>간월호철새탐조대</li><br>
                    <li>하도철새탐조대</li><br>
                    <li>탑립돌보탐조대</li>
                </ol>
            </div>
        </div>

    </div>
    </div>


</html>

```
</details>

<details>
    <summary>코드 보기(Controller)</summary>
	
```java

@GetMapping("/birdy")
    // 새의 탐조
    public String showBirds(@RequestParam(required = false) String type, Model model) {
        List<Birds> birds = birdService.showBirds(type);
        model.addAttribute("birds", birds);
        return "bird/birdybird";
    }
```
		
</details>

<details>
    <summary>코드 보기(Service)</summary>
	
```java

@Service
@RequiredArgsConstructor
public class BirdService {

    private final BirdsRepository birdsRepository;

    public List<Birds> showBirds(String type) {
        List<Birds> birds = type == null ? birdsRepository.findAll() : birdsRepository.findByType(type);
        return birds;
    }
}
```
</details>
