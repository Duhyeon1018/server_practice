package com.busanit501.springex.service;

import com.busanit501.springex.dto.TestDTO;

import java.util.List;

public interface TestService {
    void register(TestDTO testDTO);
    List<TestDTO> getAll();
}
