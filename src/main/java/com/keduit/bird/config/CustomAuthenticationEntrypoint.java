package com.keduit.bird.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntrypoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // ajax 비동기 통신의 경우 http request header에 XMLHttpRequest라는 값을 넣어줌.
        // 이 때 인증되지 않은 사용자가 ajax로 리소스 요청한 경우, "Unauthorised(401)라는 오류 메세지를 발생시킨다.
        // 나머지는 로그인을 유도(redirect)한다.

        // 401 : 권한이 필요한 페이지에 인증과정 없이 접근하려 할 경우
        // 403 : 권한필요한 페이지에 권한이 없는 사용자가 접근할 경우

        if("XMLHttpRequest".equals(request.getHeader("x-requested-with"))){
            response.sendError((HttpServletResponse.SC_UNAUTHORIZED), "Unauthorized");
//            SC_UNAUTHORIZED : 보호된 리소스에 인증되지 않은 사용자가 접근할 때,
//            에러를 일으켜 메세지를 띄운다. <-설정 : "Unauthorized"
        }else{
            response.sendRedirect("/members/login");
        }
    }
}
