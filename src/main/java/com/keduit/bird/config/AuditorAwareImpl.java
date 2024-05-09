package com.keduit.bird.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

//Spring JPA의 Auditing 기능을 이용하여 엔티티가 저장, 수정 될 때
// 자동으로 등록일, 수정일, 등록자, 수정자 등을 입력해줌.
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userid = "";
        if(authentication != null){
            //로그인한 사용자의 정보가 null이 아니라면,
            userid = authentication.getName();
            //현재 로그인한 사용자의 정보를 조회하여 사용자의 이름을 불러옴.
        }
        return Optional.of(userid);
    }

}
