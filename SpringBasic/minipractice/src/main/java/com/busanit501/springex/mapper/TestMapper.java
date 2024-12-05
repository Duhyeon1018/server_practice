package com.busanit501.springex.mapper;

import com.busanit501.springex.domain.TestVO;


import java.util.List;

public interface TestMapper {

    String getTime();

    void insert(TestVO testVO);

    List<TestVO> selectAll();

    TestVO selectOne(Long tno);

    void delete(Long tno);

    void update(TestVO testVO);
}





