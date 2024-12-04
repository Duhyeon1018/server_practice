package com.busanit501.springex.service;

import com.busanit501.springex.dto.TodoDTO;
import java.util.List;

public interface TodoService {
    void register(TodoDTO todoDTO);
    List<TodoDTO> getAll();
}
