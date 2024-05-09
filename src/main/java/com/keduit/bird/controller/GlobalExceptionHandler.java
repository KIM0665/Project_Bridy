package com.keduit.bird.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
//전역 예외처리기
    // 예외가 발생할 때 응답이 커밋되었는 지 확인하고, 커밋되지 않은 경우에만 오류 페이지 리턴.

    @ExceptionHandler(Exception.class)
    public String handleException() {
        return "error"; // 에러 페이지의 뷰 이름을 리턴합니다.
    }
}