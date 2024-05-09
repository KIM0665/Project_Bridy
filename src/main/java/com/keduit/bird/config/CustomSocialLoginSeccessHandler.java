package com.keduit.bird.config;

import com.keduit.bird.dto.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Log4j2
public class CustomSocialLoginSeccessHandler implements AuthenticationSuccessHandler {

    private final PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("===========onAuthenticationSuccess===========");
        log.info(authentication.getPrincipal());
        //유저 정보

        //담기
//        MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
//        String encodedPw = memberSecurityDTO.getPassword();
        if (authentication.getPrincipal() instanceof MemberSecurityDTO) {
            MemberSecurityDTO memberSecurityDTO = (MemberSecurityDTO) authentication.getPrincipal();
            String encodedPw = memberSecurityDTO.getPassword();

            if (memberSecurityDTO.isSocial()) {
                log.info("비밀번호를 변경할 로직을 입력.");
                log.info("받아온 새 비밀번호를 member에 업데이트.");
                response.sendRedirect("/");
            } else {
                response.sendRedirect("/");
            }
        }else {
            // Principal이 MemberSecurityDTO가 아닌 경우 처리
            // 예: 소셜 로그인을 통한 경우
            response.sendRedirect("/");
        }
    }

}
