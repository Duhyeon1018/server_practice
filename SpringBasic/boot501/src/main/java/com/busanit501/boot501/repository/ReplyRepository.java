package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Reply;
import com.busanit501.boot501.dto.BoardListReplyCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    // 기본적인 crud, 쿼리스트링으로 가능함.

    //검색기능 추가해보기.
    @Query("select r from Reply r where r.board.bno = :bno")
    Page<Reply> listOfBoard(Long bno, Pageable pageable);
}
