package org.jh.board.repository;

import org.jh.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByNickname(String nickname);
    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);

    Optional<Member> findByEmailAndPassword(String email, String password);

    @Query("select m,count(b.id) from Member m join Board b on b.member = m where m.nickname = :nickname")
    List<Object[]> testQuery(@Param("nickname") String nickname);
}
