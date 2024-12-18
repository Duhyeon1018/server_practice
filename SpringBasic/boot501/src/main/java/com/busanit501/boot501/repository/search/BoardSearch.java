package com.busanit501.boot501.repository.search;

import com.busanit501.boot501.domain.Board;
import com.busanit501.boot501.dto.BoardListReplyCountDTO;
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

    // 댓글 갯수를 포함한 목록, 작업 1
    // 목록 , board를 조회를 함.
    // 단점= board 같은 경우는 reply 쪽으로 연관관계가 설정이 안되어있음
    // 즉, JAVA INSTANCE로 조회를 못함
    // 그래서 두개의 테이블을 연결 조인(외부조인 함. 왜? 댓글이 null 일수도 있기 때문)
    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);
}
