package com.keduit.bird.service;


import com.keduit.bird.constant.Role;
import com.keduit.bird.dto.MemberSecurityDTO;
import com.keduit.bird.entity.Member;
import com.keduit.bird.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Log4j2
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("-----_____CustomOAuth2UserService-----_____");
        log.info(userRequest);

        log.info("======================================");
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();
        log.info("*****name : " + clientName);

        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> paramMap = oAuth2User.getAttributes();

//        paramMap.forEach((k, v)-> {
//            log.info("==================paramMap==================");
//            log.info(k + ":" + v);
////            여기서 사이트 등록 id, 카카오 닉네임, 이메일 확인 가능
//        });
////        이건 카카오만 쓸 경우.

        String memberEmail = null;
        switch(clientName){
            case "kakao":
                memberEmail = getKakaoEmail(paramMap);
                break;
        }
        log.info("=====email=====");
        log.info(memberEmail);
        log.info("===============");


//        return oAuth2User;
        return generateDTO(memberEmail, paramMap);
    }

    private MemberSecurityDTO generateDTO(String memberEmail, Map<String, Object> paramMap) {
        Member result = memberRepository.findByMemberEmail(memberEmail);
        if(result == null){
            //회원 추가
            Member member = new Member();
            member.setMemberName("kakao");
            member.setMemberEmail(memberEmail);
            member.setMemberPwd(passwordEncoder.encode("1111"));
            member.setSocial(true);
            member.setRole(Role.MEMBER);
            memberRepository.save(member);

            // MemberSecurityDTO 구성 및 반환 추가
            MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
                    memberEmail, "1111",true,
                    Arrays.asList(new SimpleGrantedAuthority("ROEL_MEMBER"))
            );
            memberSecurityDTO.setProps(paramMap);
            return memberSecurityDTO;
        }else{
            MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
            result.getMemberEmail(),
            result.getMemberPwd(),
            result.isSocial(),
            Arrays.asList(new SimpleGrantedAuthority("ROEL_MEMBER"))
            );
            return memberSecurityDTO;
        }
    }
    private String getKakaoEmail(Map<String, Object> paramMap) {
        log.info("======== 카카오 로그인 ============");
        Object value=paramMap.get("kakao_account");
        log.info(value);

        //이메일 뽑아오기
        LinkedHashMap accountMap = (LinkedHashMap)value;
        String memberEmail = (String)accountMap.get("email");
        log.info("@@ = " + memberEmail);
        return memberEmail;
    }
}
