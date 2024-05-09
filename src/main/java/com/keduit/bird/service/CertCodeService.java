package com.keduit.bird.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class CertCodeService {

    // 멤버 이메일과 발송된 인증 코드를 저장하는 맵
    private final Map<String, String> certCodes = new HashMap<>();

    // 발송된 인증 코드를 저장하는 메서드
    public void saveCertCode(String memberEmail, String certCode) {
        // 이메일과 코드가 모두 null이 아닌지 확인
        if (memberEmail != null && certCode != null) {
            // 이메일과 발송된 코드를 맵에 저장
            certCodes.put(memberEmail, certCode);
            System.out.println("*메일주소 : " + memberEmail + " *코드 : " + certCode);
        } else {
            // 이메일이나 코드가 null인 경우 오류 처리
            System.out.println("이메일 또는 코드가 null입니다.");
        }
    }

    // 저장된 인증 코드를 반환하는 메서드
    public String getCertCode(String memberEmail) {
        System.out.println("*****## : " + certCodes);
        return certCodes.get(memberEmail);
    }

    // 인증이 완료된 후 사용된 코드를 맵에서 삭제하는 메서드
    public void deleteCertCode(String memberEmail) {
        certCodes.remove(memberEmail);
    }
}
