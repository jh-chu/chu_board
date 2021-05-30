package org.jh.board.service;

import org.assertj.core.api.Assertions;
import org.jh.board.dto.BoardDto;
import org.jh.board.entity.Board;
import org.jh.board.entity.Member;
import org.jh.board.repository.BoardRepository;
import org.jh.board.repository.MemberRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BoardServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardRepository boardRepository;

    @BeforeAll
    public void insertData1() {
        IntStream.rangeClosed(1,10).forEach(i->{
            Member member = Member.builder()
                    .email("cnwlsguq" + i + "@naver.com")
                    .nickname("츄" + i)
                    .build();
            memberRepository.save(member);
        });

        IntStream.rangeClosed(1,100).forEach(i->{
            Board board = Board.builder()
                    .title("게시글 " + i + "번")
                    .content("게시글 " + i + "번째 내용입니다.")
                    .member(memberRepository.findByNickname("츄" + ((i % 10)==0?10:i%10)))
                    .build();
            boardRepository.save(board);
        });
    }

    @Test
    @DisplayName("입력확인 테스트")
    @Rollback(value = false)
    public void basicTest() throws Exception{

        List<Member> findMember = memberRepository.findAll();
        List<Board> findBoard = boardRepository.findAll();

        assertThat(findMember.size()).isEqualTo(10);
        assertThat(findBoard.size()).isEqualTo(100);

    }

    @Test
    public void getBoardList() throws Exception{

        Page<Board> result = boardRepository.findAll(PageRequest.of(0, 10, Sort.by("createDate")));
        List<BoardDto> list = result.stream().map(BoardDto::new).collect(Collectors.toList());

        for (BoardDto boardDto : list) {
            System.out.println("boardDto = " + boardDto);
        }

    }

    @Test
    @Rollback(false)
    public void insertData() throws Exception{
        IntStream.rangeClosed(101,300).forEach(i->{
            Board board = Board.builder()
                    .title("게시글 " + i + "번")
                    .content("게시글 " + i + "번째 내용입니다.")
                    .member(memberRepository.findByNickname("츄" + (i % 11)))
                    .build();
            boardRepository.save(board);
        });

    }
}