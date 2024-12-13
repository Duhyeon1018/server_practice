package com.busanit501.bootpractice.service;


import com.busanit501.bootpractice.dto.FoodDTO;
import com.busanit501.bootpractice.dto.PageRequestDTO;
import com.busanit501.bootpractice.dto.PageResponseDTO;

public interface FoodService {
    Long register(FoodDTO foodDTO);
    FoodDTO readOne(Long bno);
    void update(FoodDTO foodDTO);
    void delete(Long bno);
    PageResponseDTO<FoodDTO> list(PageRequestDTO pageRequestDTO);
}
