package org.jh.board.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jh.board.dto.BoardDto;
import org.jh.board.dto.CommentDto;
import org.jh.board.dto.RequestCondition;
import org.jh.board.dto.PageResultDto;
import org.jh.board.entity.Board;

import org.jh.board.entity.Comment;
import org.jh.board.repository.BoardRepository;
import org.jh.board.repository.CommentRepository;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;



@Log4j2
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
    public PageResultDto<BoardDto, Board> getBoardList(RequestCondition requestCondition) {
        log.info("BoardService.getBoardList");

//        Pageable pageable = requestCondition.getPageable();
        Pageable pageable = PageRequest.of(0, 10);

        Page<Board> result = boardRepository.findAll(pageable);

        Function<Board, BoardDto> fn = (BoardDto::new);

        return new PageResultDto<>(result, fn);
    }



    @Override
    public Page<BoardDto> getBoardList(String type, String keyword, Pageable pageable) {
        return boardRepository.searchBoardPage(type, keyword, pageable);
    }







    @Override
    public BoardDto getBoardWithComments(Long boardId) {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("createDate").descending());
        return getBoardWithComments(boardId, pageable);
    }

    @Override
    public BoardDto getBoardWithComments(Long boardId, Pageable commentPageable) {

        Optional<Board> findBoard = boardRepository.findById(boardId);

        BoardDto boardDto = BoardDto.builder().build();

        if(findBoard.isPresent()) {
            Page<Comment> findComments = commentRepository.findByBoardId(boardId, commentPageable);
            Page<CommentDto> commentDto = findComments.map(CommentDto::new);
            boardDto = findBoard.map(BoardDto::new).get();
            boardDto.setComments(commentDto);
        }

        return boardDto;
    }


}
