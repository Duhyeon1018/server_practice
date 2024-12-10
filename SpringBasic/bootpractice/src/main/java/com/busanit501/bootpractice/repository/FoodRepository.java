package com.busanit501.bootpractice.repository;

import com.busanit501.bootpractice.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

//extends JpaRepository<Board, Long> -> 기본 쿼리 메소드 이용해서,
// 간단한 crud 디비 작업은, 메서드를 이용해서 처리가 가능함.

public interface FoodRepository extends JpaRepository<Food, Long> {
    // 아무메서드가 없음
    // 하지만, 우리는 기본 탑재된 쿼리 메소드를 활용할 예정
}
