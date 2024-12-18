package com.busanit501.boot501.service;

import com.busanit501.boot501.dto.BoardDTO;
import com.busanit501.boot501.dto.BoardListReplyCountDTO;
import com.busanit501.boot501.dto.PageRequestDTO;
import com.busanit501.boot501.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Log4j2
@SpringBootTest
public class ServiceTests {
    @Autowired
    private BoardService boardService;

    @Test
    public void testRegisterBoard() {
        // 더미데이터 필요, 임시 DTO 생성
        BoardDTO boardDTO = BoardDTO.builder()
                .title("오늘 점심 뭐 먹지?")
                .content("라면먹어야지")
                .writer("이상용")
                .regDate(LocalDateTime.now())
                .build();
        Long bno = boardService.register(boardDTO);
        log.info("입력한 게시글 번호: " + bno.toString());
    }


    @Test
    public void testSelectOneBoard() {
        // 더미 데이터 필요, 임시 DTO 생성.
        Long bno = 103L;
        BoardDTO boardDTO= boardService.readOne(bno);
        log.info("testSelectOneBoard , 하나 조회 boardDTO: " + boardDTO.toString());
    }

    @Test
    public void testUpdateBoard() {
        //수정할 더미 데이터 필요
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(103L)
                .title("수정할 오늘 점심 뭐먹지?")
                .content("칼국수 먹을까?")
                .build();
        boardService.update(boardDTO);
    }

    @Test
    public void testDeleteBoard() {
        boardService.delete(103L);
    }


    @Test
    public void testSelectAllBoard() {
        // 검색할 더미 데이터
        // 준비물 1) PageRequestDTO, 키워드, 페이지, 사이즈 정보가 다 있음.
        PageRequestDTO pageRequestDTO =
                PageRequestDTO.builder()
                        .page(1)
                        .type("tcw")
                        .keyword("샘플")
                        .size(10)
                        .build();

        PageResponseDTO<BoardDTO> list = boardService.list(pageRequestDTO);
        log.info("list: " + list.toString());
    }

    @Test
    @Transactional
    public void testSelectAllBoardWithReplyCount() {
        // 검색할 더미 데이터
        // 준비물 1) PageRequestDTO, 키워드, 페이지, 사이즈 정보가 다 있음.
        PageRequestDTO pageRequestDTO =
                PageRequestDTO.builder()
                        .page(1)
                        .type("tcw")
                        .keyword("샘플")
                        .size(10)
                        .build();

        PageResponseDTO<BoardListReplyCountDTO> list = boardService.listWithReplyCount(pageRequestDTO);
        log.info("list: " + list.toString());
    }

}
