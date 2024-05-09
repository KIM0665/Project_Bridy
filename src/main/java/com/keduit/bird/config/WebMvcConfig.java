package com.keduit.bird.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${uploadPath}")
    String uploadPath;

    private String savePath = "file:///C:/pj/members/";
    // application.properties 에 설정한 "uploadPath" 프로퍼티의 값을 읽어옴.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/images/**")
                .addResourceLocations(uploadPath);
    // Url이 "/images/**" 로 시작하는 경우(접근경로),
    // uploadPath에 설정한 폴더를 기준으로 파일을 읽도록 설정하기.
    // .addResourceLocations(uploadPath) : 로컬 컴퓨터에서 읽어올 경로(루트)를 설정함.
    // uploadPath : 실제 파일 경로
    }
}
