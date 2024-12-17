package com.busanit501.boot501.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class BoardListReplyCountDTO {
    private  Long bno;
    private  String title;
    private  String content;
    private  String writer;
    private LocalDateTime regDate;

    // 댓글의 갯수
    private Long replyCount;

}
