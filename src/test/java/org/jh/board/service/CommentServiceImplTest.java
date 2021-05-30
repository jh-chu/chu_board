package org.jh.board.service;

import org.jh.board.dto.CommentDto;
import org.jh.board.entity.Board;
import org.jh.board.entity.Comment;
import org.jh.board.entity.Member;
import org.jh.board.repository.BoardRepository;
import org.jh.board.repository.CommentRepository;
import org.jh.board.repository.MemberRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommentServiceImplTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommentService commentService;


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

            if(i%10 == 2) {
                for (int j = 0; j < 10; j++) {
                    Comment comment = Comment.builder()
                            .board(board)
                            .member(memberRepository.findByNickname("츄" + ((j % 10) == 0 ? 10 : j % 10)))
                            .content(j + "번째 댓글입니다.")
                            .build();
                    commentRepository.save(comment);
                }
            }

        });

    }

    @Test
    public void comment() throws Exception{

        Page<CommentDto> result = commentService.getCommentList(12L);

        List<CommentDto> list = result.getContent();

        for (CommentDto commentDto : list) {
            System.out.println("commentDto = " + commentDto);
        }

    }
}