package com.busanit501.bootpractice.controller;

import com.busanit501.bootpractice.dto.FoodDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.dto.PageResponseDTO;
import com.busanit501.bootpractice.service.FoodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/food")
@RequiredArgsConstructor
// http://localhost:8080/food, 시작하겠다.
public class FoodController {
    private final FoodService foodService;
    // http://localhost:8080/food/list
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model ) {
        // 서비스 이용해서, 데이터베이스 목록 페이징 처리해서 가져오기.
        // 앞단 화면에서, 검색어:keyword 내용, 페이징 내용(page = 1) 담아서 전달.
        PageResponseDTO<FoodDTO> responseDTO = foodService.list(pageRequestDTO);
        log.info("pageRequestDTO 의 getLink 조사 : " + pageRequestDTO.getLink());
        model.addAttribute("responseDTO", responseDTO);
    }

    //등록 작업, 1) 등록화면 2) 로직처리
    @GetMapping("/register")
    public void register() {

    }
    @PostMapping("/register")
    public String registerPost(@Valid FoodDTO foodDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("FoodController register post 로직처리: ");
        log.info("FoodController register post  foodDTO : " + foodDTO);

        // 유효성 체크 -> 유효성 검증시, 통과 안된 원인이 있다면,
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/register";
        }
        //검사가 통과가 되고, 정상 입력
        Long bno = foodService.register(foodDTO);

        // 글 정상 등록후, 화면에 result 값을 전달하기.
        // 1회용 사용하기.
        redirectAttributes.addFlashAttribute("result", bno);
        redirectAttributes.addFlashAttribute("resultType", "register");

        return "redirect:/food/list";

    }

    @GetMapping("/read")
    public void read(Long bno, PageRequestDTO pageRequestDTO,
                     Model model) {
        FoodDTO foodDTO = foodService.readOne(bno);
        model.addAttribute("dto", foodDTO);
    }

    @GetMapping("/update")
    public void update(Long bno, PageRequestDTO pageRequestDTO,
                       Model model) {
        FoodDTO foodDTO = foodService.readOne(bno);
        model.addAttribute("dto", foodDTO);
    }

    @PostMapping("/update")
    public String updatePost(@Valid FoodDTO foodDTO,
                             BindingResult bindingResult,
                             PageRequestDTO pageRequestDTO,
                             String keyword2,String page2, String type2,
                             RedirectAttributes redirectAttributes) {
        log.info("FoodController updatePost post 로직처리: ");
        log.info("FoodController updatePost post  foodDTO : " + foodDTO);

        log.info("FoodController updatePost post  pageRequestDTO : " + pageRequestDTO);

        // 유효성 체크 -> 유효성 검증시, 통과 안된 원인이 있다면,
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            // 1회용으로, 웹 브라우저에서, errors , 키로 조회 가능함. -> 뷰 ${errors}
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/food/update?bno="+foodDTO.getBno()+"&keyword="+keyword2+"&page="+page2+"&type="+type2;
        }
        //검사가 통과가 되고, 정상 입력
        foodService.update(foodDTO);

        // 글 정상 등록후, 화면에 result 값을 전달하기.
        // 1회용 사용하기.
        redirectAttributes.addFlashAttribute("result", foodDTO.getBno());
        redirectAttributes.addFlashAttribute("resultType", "update");

        return "redirect:/food/read?bno="+foodDTO.getBno()+"&keyword="+keyword2+"&page="+page2+"&type="+type2;

    }

    @PostMapping("/delete")
    public String delete(Long bno,
                         String keyword2,String page2, String type2,
                         RedirectAttributes redirectAttributes) {
        foodService.delete(bno);
        redirectAttributes.addFlashAttribute("result", bno);
        redirectAttributes.addFlashAttribute("resultType", "delete");
        return "redirect:/food/list?"+"&keyword="+keyword2+"&page="+page2+"&type="+type2;
    }

}
