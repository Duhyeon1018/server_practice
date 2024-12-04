package com.busanit501.springex.controller;


import com.busanit501.springex.dto.TestDTO;
import com.busanit501.springex.service.TestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller// 1)화면 2)데이터 제공.
@RequestMapping("/test")
// 웹브라우저에서 아래의 경로로 오는 url 전부 여기 컨트롤러가 받아서 작업함.
// localhost:8080/test/
@Log4j2
@RequiredArgsConstructor
public class TestController {


    private final TestService testService;



    // localhost:8080/todo/list
    @RequestMapping("/list")
    public void list(Model model) {
        log.info("TestController list : 화면제공은 해당 메서드 명으로 제공함.");
        List<TestDTO> list = testService.getAll();
        log.info("TestController list 데이터 유무 확인 :" + list);
        //데이터 탑재. 서버 -> 웹
        model.addAttribute("list", list);

    }

    // localhost:8080/todo/register
    // 1) 글작성 폼, 화면 -> get
    // 2) 글작성 로직 처리 -> post
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {
        log.info("TestController register : 화면제공은 해당 메서드 명으로 제공함.");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    //@Valid TodoDTO todoDTO : 검사 대상 클래스,
    // BindingResult bindingResult : 검사 결과의 오류를 모아두는 임시 공간
    // RedirectAttributes redirectAttributes : 서버 -> 웹 , 데이터 전달하는 도구
    public String registerPost(@Valid TestDTO testDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("TestController register post 로직처리: ");
        log.info("TestController register post  testDTO : " + testDTO);

        // 유효성 체크 -> 유효성 검증시, 통과 안된 원인이 있다면,
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/test/register";
        }
        //검사가 통과가 되고, 정상 입력
        testService.register(testDTO);

        return "redirect:/test/list";
    }


    @RequestMapping("/read")
    public void read(Long tno, Model model) {
        log.info("TestController read :");
        TestDTO testDTO = testService.getOne(tno);
        log.info("TestController read 데이터 유무 확인 :" + testDTO);
        //데이터 탑재. 서버 -> 웹
        model.addAttribute("testDTO", testDTO);

    }


    // 수정 1) 폼 2) 로직 처리
    @RequestMapping("/update")
    public void update(Long tno, Model model) {
        log.info("TestController read :");
        TestDTO testDTO = testService.getOne(tno);
        log.info("TodoController read 데이터 유무 확인 :" + testDTO);
        //데이터 탑재. 서버 -> 웹
        model.addAttribute("testDTO", testDTO);

    }

    // 삭제
    @PostMapping("/delete")
    public String delete(Long tno) {
        testService.delete(tno);
        return "redirect:/test/list";
    }
}








