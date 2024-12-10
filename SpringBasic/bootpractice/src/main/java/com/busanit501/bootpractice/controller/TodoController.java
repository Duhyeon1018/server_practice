package com.busanit501.bootpractice.controller;

import com.busanit501.bootpractice.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller // 1)화면 제공 2) 데이터 제공
@Log4j2
public class TodoController {

    @GetMapping("/todo")
    public void todo(Model model) {
        model.addAttribute("msg", "hello world");
}

    @GetMapping("/todo/list")
    public void list(Model model) {
        List<String> list = Arrays.asList("a", "b", "c");
        model.addAttribute("list", list);
    }

    @GetMapping("/todo/register")
    public void register(Model model) {
        List<String> strList = IntStream.range(1, 10)
                .mapToObj(i -> "임시 데이터 " +i)
                .collect(Collectors.toList());
        model.addAttribute("strList", strList);

        Map<String,String> map = new HashMap<>();
        map.put("a", "aaa");
        map.put("b", "bbb");
        model.addAttribute("map", map);

        TodoDTO todoDTO = TodoDTO.builder()
                .t1("테스트 t1")
                .t2("테스트 t2")
                .t3("테스트 t3")
                .t4("테스트 t4")
                .build();
        model.addAttribute("todoDTO", todoDTO);

    }

}
