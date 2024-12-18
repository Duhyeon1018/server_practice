package com.busanit501.boot501.service;

import com.busanit501.boot501.dto.BoardDTO;
import com.busanit501.boot501.dto.PageRequestDTO;
import com.busanit501.boot501.dto.PageResponseDTO;
import com.busanit501.boot501.dto.ReplyDTO;

public interface ReplyService {
    Long register(ReplyDTO replyDTO); //Rest로 하고있기 때문에 long 으로 반환
    ReplyDTO readOne(Long rno);
    void update(ReplyDTO replyDTO);
    void delete(Long rno);
//    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
