package com.busanit501.springex.mapper;

import com.busanit501.springex.domain.TodoVO;
import com.busanit501.springex.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {

    String getTime();

    void insert(TodoVO todoVO);

    List<TodoVO> selectAll();

    TodoVO selectOne(Long tno);

    void delete(Long tno);

    void update(TodoVO todoVO);

    //페이징한 전체목록
    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);

    // 페이징 위해서, 전체갯수를 알고 있어야함
    int getCount(PageRequestDTO pageRequestDTO);
}





