package com.busanit501.boot501.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
//복합키 인덱스, 순서 중요함
// 순서, 댓글 번호로 1차 기준정렬 , 게시글 번호로 2차 기준정렬
// 검색시, 전체 검색을 안하고 인덱스(색인) 검색(index scan)
// 만들어두는 이유 = 검색 시 성능 향상을 위해서 함
// 주의사항, 너무 복잡한 복합키 설정이나, 또는 , 많은 복합키를 사용하게 되면
// DB 자체에서 부하가 걸려서 효율적이지 못함. 가급적 너무 많은 복합키 쓰지 않도록 주의

@Table(name = "Reply", indexes = {
        @Index(name = "idx_reply_board_bno", columnList = "board_bno")
})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board") // db에 접근시, 두번 접근을 안함 << 안하면 단위테스트 할 때 오류뜸(제외)
// 단위테스트 기본 설정상, 하나의 테이블에 한번 접근함.
// (exclude = "board"), 를 사용안하고
// 다른 테이블도 같이 조회를 해야한다면, @Transaction설정
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;// 댓글의 구분 번호,

    //중요, 여기서 연관관계 설정, 외래키 설정
    // DTO(화면) , ENTITY(DB) << 구분하는게 DB단에서 일하는거랑 화면에서 표현할거랑 다름
    // 그래서 구분 해야함.
    // 마치, 데이터베이스에서 설정하는 것과 비슷
    @ManyToOne(fetch = FetchType.LAZY) // fetch = 가져오는거 , LAZY = 사용하는 시점에 조회함(늦게 가져오는거)
    //FetchType.EAGER, 즉시로딩, 사용 안해도 조회 하겠다.
    private Board board; // 부모의 게시글 번호,

    @NotEmpty
    private String replyText;

    @NotEmpty
    private String replier;
}
