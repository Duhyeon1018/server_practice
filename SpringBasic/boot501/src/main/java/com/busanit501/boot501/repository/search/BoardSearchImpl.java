package com.busanit501.boot501.repository.search;

import com.busanit501.boot501.domain.Board;
import com.busanit501.boot501.domain.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

// 반드시 이름 작성시: 인터페이스이름 + Impl
// QuerydslRepositorySupport 의무 상속,
// 만든 인터페이스 구현하기.
public class BoardSearchImpl extends QuerydslRepositorySupport
        implements BoardSearch {


    // 부모 클래스 초기화, 사용하는 엔티티 클래스를 설정.
    // Board
    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    // 자바 문법으로 , sql 문장 명령어 대체해서 전달중.
    // 1) where
    // 2) 페이징
    // 3) 제목, 내용, 조건절. 추가중.
    public Page<Board> search(Pageable pageable) {
        //예시,
        QBoard board = QBoard.board; // Q 도메인 객체, 엔티티 클래스(= 테이블)
        JPQLQuery<Board> query = from(board); // select * from board 한 결과와 비슷함.
        //select * from board 작성한 내용을 query 객체 형식으로 만듦.
        // 다양한 쿼리 조건을 이용할수 있음.
        // 예, where, groupby, join , pagination
        query.where(board.title.contains("3"));
        // =================================.,조건1

        // 제목, 작성자 검색 조건 추가, = mybatis, spl, 자바 문법 세가지 있는데 이건 자바문법
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(board.title.contains("3"));// "3" 제목 임시
        booleanBuilder.or(board.content.contains("7"));// "3" 제목 임시
        // query, 해당 조건을 적용함.
        query.where(booleanBuilder);
        // 방법2, 추가 조건으로, bno 가 0보다 초과 하는 조건.
        query.where(board.bno.gt(0L));

        // =================================.,조건3

        // 페이징 조건 추가하기. qeury에 페이징 조건을 추가한 상황
        this.getQuerydsl().applyPagination(pageable, query);
        // =================================.,조건2

        // 해당 조건의 데이터를 가져오기,
        List<Board> list = query.fetch();
        // 해당 조건에 맞는 데이터의 갯수 조회.
        long total = query.fetchCount();
        //

        return null;
    }

    @Override
    //String[] types, "t","c","tc"
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
        QBoard board = QBoard.board; // Q 도메인 객체, 엔티티 클래스(= 테이블)
        JPQLQuery<Board> query = from(board);
        // select * from board
        if(types != null && types.length > 0 && keyword != null) {
            //여러 조건을 하나의 객체에 담기
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            for(String type : types) {
                switch(type) {
                    case "t":
                        booleanBuilder.or(board.title.like("%" + keyword + "%"));
                        break;
                    case "c":
                        booleanBuilder.or(board.content.like("%" + keyword + "%"));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.like("%" + keyword + "%"));
                        break;
                } // switch
            } //and for
            //where 조건을 적용해보기
            query.where(booleanBuilder);
        } //end if
        // bno > 0
        query.where(board.bno.gt(0L));
        // where 조건

        // 페이징 조건
        // 페이징 조건 추가하기. qeury에 페이징 조건을 추가한 상황
        this.getQuerydsl().applyPagination(pageable, query);

        // ====================================================
        // 위의 조건으로, 검색조건으로 1) 페이징된 결과물 받아오기 2) 페이징된 전체 갯수
        // 해당 조건의 데이터를 가져오기,
        List<Board> list = query.fetch();
        // 해당 조건에 맞는 데이터의 갯수 조회.
        long total = query.fetchCount();

        // 마지막은 Page 타입으로 전달해주기
        Page<Board> result = new PageImpl<Board>(list, pageable, total);

        return result;
    }


}
