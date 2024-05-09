package com.keduit.bird.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.security.SecureRandom;

@Service
public class EmailService {

    // 이메일 발송 작업을 위한 객체 생성
    @Autowired
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String memberEmail, String emailCode){
        //발송할 메일 내용 구성하기.
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(memberEmail);
        message.setSubject("[Birdy] 가입 인증번호입니다.");
        message.setText("인증번호 : [ " + emailCode + " ]");
        javaMailSender.send(message);
        //설정한 내용의 메세지 전송send 하기.
    }

    public String generateCode(){
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder certCode = new StringBuilder(6);
        // 보안기능으로 쉽게 유추해낼 수 없는 시큐어랜덤 클래스 사용.
        //내용 : 코드의 길이 제한 (6자리) 설정.

        for(int i = 0; i < 6; i++){
            certCode.append(secureRandom.nextInt(10));
            //0~9까지 10개의 숫자를 코드 길이만큼 반복 생서하여 6자리 숫자 생성함.
        }
        return certCode.toString();
        //String 타입으로 생성하였고, 나중에 내용 비교를 위해서 String 타입으로 반환시킴.
    }
}
