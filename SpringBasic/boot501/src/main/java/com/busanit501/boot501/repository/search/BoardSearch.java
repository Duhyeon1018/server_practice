package com.busanit501.boot501.repository.search;

import com.busanit501.boot501.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// Querydsl 이용시, 해당 인터페이스에서, Jq
public interface BoardSearch {
    // 연습용으로, 자바문법으로 SQL 문장 전달해보기
    Page<Board> search(Pageable pageable);

    //String[] types, "t","c","tc"로 검색할거냐
    //Pageable -> 페이징 하기위한 재료, 현재페이지, 페이지 보여줄갯수,정렬 << 세개가 담아져있음. 그래서 알아서 처리
    // Page -> 1)페이징 된 결과물 10개 2) 전체갯수 3) 현재페이지 등 정보조회가능
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
}
