package com.busanit501.bootpractice.repository;

import com.busanit501.bootpractice.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // 기본적인 crud , 쿼리 스트링으로 가능함.

    // 검색 기능 추가해보기. @JPQL << 씀.
    @Query("select r from Reply r where r.food.bno = :bno")
    Page<Reply> listOfFood(Long bno, Pageable pageable);


}
