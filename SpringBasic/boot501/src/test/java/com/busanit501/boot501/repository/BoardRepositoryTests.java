package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {

    @Autowired
    // 아무 메소드가 없지만, 기본 탑재된 쿼리 메소드 이용해서, crud  해보기.
    private BoardRepository boardRepository;

    @Test
    public void testInsert() {
        // 더미 데이터, 앞에서, 병렬 처리 기능 사용하기.
        // stream 클래스 이용하기.
        // 1 ~ 99번까지 생성해요.
        IntStream.range(1, 100).forEach(i -> {
            Board board = Board.builder()
                    .title("샘플 제목 : " + i)
                    .content("샘플 내용 : " + i)
                    .writer("샘플 작성자 : lsy "+i)
                    .build();
            // crud, insert , save-> 1차 임시 테이블 저장 -> 실제 테이블 반영
            Board result = boardRepository.save(board);
            log.info("추가된 bno 번호 : " + result);
        });
    }

    @Test
    public void testSelectOne(){
        Long bno = 99L;
        //Optional, 있으면 해당인스턴스 가져오고 , 없으면 null 입니다.
       Optional<Board> result = boardRepository.findById(bno);
       // result 있으면 , Board 타입으로 받고, 없으면, 예외 발생시킴
       Board board = result.orElseThrow();
       log.info("하나 조회 : " + board );

    }


    @Test
    public void testSelectAll() {

        List<Board> result = boardRepository.findAll();
        // result 있으면, Board 타입으로 받고, 없으면, 예외 발생시킴.
        for (Board board : result) {
            log.info(board);
        }


    }

    @Test
    public void testUpdate() {
        Long bno = 99L;
        // 수정 할 데이터가 해당 테이블에 있는지 조회 부터 하기.
        Optional<Board> result = boardRepository.findById(bno);
        // result 있으면, Board 타입으로 받고, 없으면, 예외 발생시킴.
        // board, 엔티티 클래스 인스턴스가, 하나의 데이터베이스의 내용임.
        Board board= result.orElseThrow();
        board.changeTitleContent("변경 제목 ","변경 내용");
        // 실제 디비 테이블 반영.
        // 순서 -> 1차 영속성 컨텍스트(임시 테이블) 적용 -> 실제 테이블 반영.
        // save -> 해당 실제 테이블이 없다면 -> insert
        // save -> 해당 실제 테이블이 있다면 -> update
        boardRepository.save(board);

    }

    @Test
    public void testDelete() {
        Long bno = 99L;
        boardRepository.deleteById(bno);
    }

    @Test
    public void testPaging() {

        Pageable pageable =  PageRequest.of(0, 10,
                Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(pageable);
        log.info("result.getTotalElements()전체 갯수 :" +result.getTotalElements());
        log.info("result.getTotalPages()총페이지등 :" +result.getTotalPages());
        log.info("result.getContent() 페이징된 결과물 10개 :" +result.getContent());
        log.info("result.getNumber()  현재 페이지 번호 :" +result.getNumber());
        log.info("result.getSize()  크기 :" +result.getSize());
    }


}
