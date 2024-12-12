package com.busanit501.springex.service;

import com.busanit501.springex.dto.PageRequestDTO;
import com.busanit501.springex.dto.PageResponseDTO;
import com.busanit501.springex.dto.SearchDTO;

import java.util.List;

public interface SearchService {
    void register(SearchDTO searchDTO);

    List<SearchDTO> getAll();

    //페이징 처리된 목록
    // 준비물을 준비 해둠,
    // 웹 -> 서버 , 박스
    // 웹 <- 서버, 박스
//    PageResponseDTO<SearchDTO> getListWithPage(PageRequestDTO pageRequestDTO);

    PageResponseDTO<SearchDTO> selectList(PageRequestDTO pageRequestDTO);


    SearchDTO getOne(Long tno);

    void delete(Long tno);

    void update(SearchDTO searchDTO);
}
