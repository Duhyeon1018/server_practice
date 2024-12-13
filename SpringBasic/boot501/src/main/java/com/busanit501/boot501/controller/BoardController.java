package com.busanit501.boot501.controller;

import com.busanit501.boot501.dto.PageRequestDTO;
import com.busanit501.boot501.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/board")
@RequiredArgsConstructor
// http://localhost:8080/board로 시작하겠다
public class BoardController {
    private final BoardService boardService;
    // http://localhost:8080/board/list
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        // 타임리프 작업

    }
}
