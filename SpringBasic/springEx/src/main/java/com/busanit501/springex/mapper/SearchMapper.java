package com.busanit501.springex.mapper;

import com.busanit501.springex.domain.SearchVO;
import com.busanit501.springex.domain.TodoVO;
import com.busanit501.springex.dto.PageRequestDTO;

import java.util.List;

public interface SearchMapper {

    String getTime();

    void insert(SearchVO searchVO);

    List<SearchVO> selectAll();

    SearchVO selectOne(Long tno);

    void delete(Long tno);

    void update(SearchVO searchVO);

    //페이징한 전체 목록
    List<SearchVO> selectList(PageRequestDTO pageRequestDTO);

    // 페이징 위해서, 전체갯수,
    int getCount(PageRequestDTO pageRequestDTO);
}





