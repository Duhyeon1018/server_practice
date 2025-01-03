package com.busanit501.boot501.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyDTO {

    private Long rno;// 댓글의 구분 번호,

    @NotNull
    private Long bno; // 부모의 게시글 번호,

    @NotEmpty
    private String replyText;

    @NotEmpty
    private String replier;

    private LocalDateTime regDate,modDate;


}
