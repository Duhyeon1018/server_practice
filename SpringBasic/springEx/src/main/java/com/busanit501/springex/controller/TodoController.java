package com.busanit501.springex.controller;
import com.busanit501.springex.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller//1) 화면 2) 데이터제공
@RequestMapping("/todo")
// 웹브라우저에서 아래의 경로로 오는 url 전부 여기 컨트롤러가 받아서 작업함
// localhost:8080/todo/ 모든 경로가 저기로 지정
@Log4j2
public class TodoController {

    // localhost:8080/todo/list
    @RequestMapping("/list")
    public void list() {
        log.info("list : 화면제공은 메서드명으로 제공함.");
    }


    // localhost:8080/todo/register
    //1) 글 작성 폼 -> get
    //2) 글 작성 로직처리 -> post
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {
        log.info("todoController register : 화면제공은 메서드명으로 제공함.");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerPost(TodoDTO todoDTO) {
        log.info("todoController register post 로직처리 : 화면제공은 메서드명으로 제공함.");
        log.info("todoController register post todoDTO : " + todoDTO);
    }
}
