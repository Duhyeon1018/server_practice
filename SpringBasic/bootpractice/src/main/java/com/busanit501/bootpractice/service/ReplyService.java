package com.busanit501.bootpractice.service;

import com.busanit501.bootpractice.dto.ReplyDTO;

public interface ReplyService {
    Long register(ReplyDTO replyDTO); //Rest로 하고있기 때문에 long 으로 반환
    ReplyDTO readOne(Long rno);
    void update(ReplyDTO replyDTO);
    void delete(Long rno);
//    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
