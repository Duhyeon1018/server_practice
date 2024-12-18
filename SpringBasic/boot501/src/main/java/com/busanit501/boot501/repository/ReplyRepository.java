package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Reply;
import com.busanit501.boot501.dto.BoardListReplyCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // 기본적인 crud, 쿼리스트링으로 가능함.

    //하나의 디비로 다양한 db에서 사용가능한게 장점 < 단점 : 정적인 sql 만 전달 할 수 있음.
    // 보완하기 위해서 query dsl 사용함
    // 댓글 목록 조회 해보기.
    @Query("select r from Reply r where r.board.bno = :bno")
    Page<Reply> listOfBoard(Long bno, Pageable pageable);
}