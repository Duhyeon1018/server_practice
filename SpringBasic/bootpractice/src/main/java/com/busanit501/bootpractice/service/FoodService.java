package com.busanit501.bootpractice.service;


import com.busanit501.bootpractice.dto.FoodDTO;
import com.busanit501.bootpractice.dto.FoodListReplyCountDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.dto.PageResponseDTO;

public interface FoodService {
    Long register(FoodDTO foodDTO); //Long 반환타입
    FoodDTO readOne(Long bno);
    void update(FoodDTO foodDTO);
    void delete(Long bno);
    PageResponseDTO<FoodDTO> list(PageRequestDTO pageRequestDTO);
    PageResponseDTO<FoodListReplyCountDTO> listWithReplyCount(PageRequestDTO pageRequestDTO);
}
