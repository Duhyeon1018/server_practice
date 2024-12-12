package com.busanit501.springex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SearchDTO {
    private Long tno;
    @NotEmpty // 빈 문자열 , 공백없이 값이 존재해야함
    private String title;
    @Future //미래 날짜
    private LocalDate dueDate;
    private boolean finished;
    @NotEmpty
    private String writer;
}