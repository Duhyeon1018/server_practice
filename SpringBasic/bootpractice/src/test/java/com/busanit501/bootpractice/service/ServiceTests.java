package com.busanit501.bootpractice.service;

import com.busanit501.bootpractice.dto.FoodDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@Log4j2
@SpringBootTest
public class ServiceTests {
    @Autowired
    private FoodService foodService;

    @Test
    public void testRegisterFood() {
        // 더미데이터 필요, 임시 DTO 생성
        FoodDTO foodDTO = FoodDTO.builder()
                .title("실습테스트")
                .content("실습")
                .writer("강두현")
                .regDate(LocalDateTime.now())
                .build();
        Long bno = foodService.register(foodDTO);
        log.info("입력한 게시글 번호: " + bno.toString());
    }


    @Test
    public void testSelectOneFood() {
        // 더미 데이터 필요, 임시 DTO 생성.
        Long bno = 103L;
        FoodDTO foodDTO= foodService.readOne(bno);
        log.info("testSelectOneFood , 하나 조회 foodDTO: " + foodDTO.toString());
    }

    @Test
    public void testUpdateFood() {
        //수정할 더미 데이터 필요
        FoodDTO foodDTO = FoodDTO.builder()
                .bno(103L)
                .title("미니실습 수정내용")
                .content("대머리깎아라?")
                .writer("강두현1")
                .build();
        foodService.update(foodDTO);
    }

    @Test
    public void testDeleteFood() {
        foodService.delete(103L);
    }


    @Test
    public void testSelectAllFood() {
        // 검색할 더미 데이터
        // 준비물 1) PageRequestDTO, 키워드, 페이지, 사이즈 정보가 다 있음.
        PageRequestDTO pageRequestDTO =
                PageRequestDTO.builder()
                        .page(1)
                        .type("tcw")
                        .keyword("샘플")
                        .size(10)
                        .build();

        PageResponseDTO<FoodDTO> list = foodService.list(pageRequestDTO);
        log.info("list: " + list.toString());
    }


}
