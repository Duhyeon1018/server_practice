package com.busanit501.springex.service;


import com.busanit501.springex.domain.TestVO;
import com.busanit501.springex.dto.TestDTO;
import com.busanit501.springex.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TestServiceImpl implements TestService{

    private final TestMapper testMapper;
    private final ModelMapper modelMapper;

    @Override
    public void register(TestDTO testDTO) {
        TestVO testVO = modelMapper.map(testDTO, TestVO.class);
        testMapper.insert(testVO);

    }

    // mapper , TodoVO 타입 요소로 목록을 받아옴.
    // 화면에 전달하는 타입, TodoDTO 타입으로 변환
    @Override
    public List<TestDTO> getAll() {
        List<TestDTO> list = testMapper.selectAll().stream()
                .map(vo -> modelMapper.map(vo,TestDTO.class))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public TestDTO getOne(Long tno) {
        TestVO testVO= testMapper.selectOne(tno);
        TestDTO testDTO = modelMapper.map(testVO,TestDTO.class);
        return testDTO;
    }

    @Override
    public void delete(Long tno) {
        testMapper.delete(tno);
    }

    @Override
    public void update(TestDTO testDTO) {
        TestVO testVO = modelMapper.map(testDTO,TestVO.class);
        testMapper.update(testVO);
    }


}
