package com.busanit501.springex.mapper;

import com.busanit501.springex.domain.TodoVO;

public interface TodoMapper {

    String getTime();

    void insert(TodoVO todoVO);
}
