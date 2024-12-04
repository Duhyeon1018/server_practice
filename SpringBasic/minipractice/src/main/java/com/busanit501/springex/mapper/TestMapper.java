package com.busanit501.springex.mapper;

import com.busanit501.springex.domain.TestVO;

import java.util.List;

public interface TestMapper {

    String getTime();

    void insert(TestVO testVO);

    List<TestVO> selectAll();
}
