package org.jh.board.repository;

import org.jh.board.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

//    @Test
//    public void 쿼리메소드동작() throws Exception{
//
//        String email = "cnwlsguq1@naver.com";
//        String nickname = "chu";
//
//        Member member = Member.builder()
//                .email(email)
//                .nickname(nickname)
//                .build();
//
//        memberRepository.save(member);
//
//        System.out.println(memberRepository.existsByEmail(email));
//        System.out.println(memberRepository.existsByEmail("ddd"));
//        System.out.println(memberRepository.existsByNickname(nickname));
//        System.out.println(memberRepository.existsByNickname("ggg"));
//
//
//    }

    @Test
    public void JPQL테스트() throws Exception{

        List<Object[]> result = memberRepository.testQuery("츄10");
        for (Object[] objects : result) {
            Member member = (Member)objects[0];
            Long count = (Long)objects[1];

            System.out.print("member = " + member);
            System.out.println(":   count = " + count);
        }


    }
}