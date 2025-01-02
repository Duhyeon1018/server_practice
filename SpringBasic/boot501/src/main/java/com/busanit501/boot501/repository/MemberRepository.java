package com.busanit501.boot501.repository;

import com.busanit501.boot501.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

//Member -> @Id -> String mid -> <Member, String>
public interface MemberRepository extends JpaRepository<Member, String> {
    // 회원 조회, 추가로 MemberRole 테이블을 조인해서 , 소셜 로그인이 아닌 상태
    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.mid = :mid and m.social = false")
    Optional<Member> getWithRoles(String mid);
}
