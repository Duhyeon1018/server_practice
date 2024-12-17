package com.busanit501.boot501.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
// 기본 정적 static 경로가 따로 설정,경로를 알려주기
public class CustomServletConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 웹브라우저에서 http://localhost:8080/js/test.js 에 연결시도
        // 프로젝트 폴더의 /static/js/ 로 연결시켜준다 = routing 해주는거 길잡이
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}