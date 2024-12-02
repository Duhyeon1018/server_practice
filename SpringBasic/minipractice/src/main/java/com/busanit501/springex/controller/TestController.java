package com.busanit501.springex.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class TestController {

    @GetMapping("/test")
    // 확인. http://localhost:8080/hello
    // 매핑이 해당 메서드명과 동일한 뷰 파일로 연결됨
    // /WEB-INF/views/
    // hello(메서드명)
    // .jsp
    public void test() {
        //아직 화면이 없어서 임의로 만들기
        log.info("test");
    }

    @GetMapping("/test2")
    //만약에 리턴 타입이 문자열이면, 해당 문자열의 이름이
    // 뷰의 파일명으로 , 뷰를 매핑, 할당

    public String test2() {
        //아직 화면이 없어서 임의로 만들기
        log.info("test2");
        return "Test2";
    }
}
