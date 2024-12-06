package com.busanit501.springex.mapper;

import com.busanit501.springex.domain.TestVO;
import com.busanit501.springex.dto.PageRequestDTO;


import java.util.List;

public interface TestMapper {

    String getTime();

    void insert(TestVO testVO);

    List<TestVO> selectAll();

    TestVO selectOne(Long tno);

    void delete(Long tno);

    void update(TestVO testVO);

    //페이징한 전체목록
    List<TestVO> selectList(PageRequestDTO pageRequestDTO);

    // 페이징 위해서, 전체갯수를 알고 있어야함
    int getCount(PageRequestDTO pageRequestDTO);
}





