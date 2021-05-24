package org.jh.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jh.board.dto.BoardDto;
import org.jh.board.dto.PageRequestDto;
import org.jh.board.dto.PageResultDto;
import org.jh.board.entity.Board;
import org.jh.board.repository.BoardRepository;
import org.jh.board.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Log4j2
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
    public PageResultDto<BoardDto, Board> getBoardList(PageRequestDto pageRequestDto) {
        log.info("BoardService.getBoardList");

        Pageable pageable = pageRequestDto.getPageable(Sort.by("id").ascending());

        Page<Board> result = boardRepository.findAll(pageable);

        Function<Board, BoardDto> fn = (BoardDto::new);

        return new PageResultDto<>(result, fn);
    }
}
