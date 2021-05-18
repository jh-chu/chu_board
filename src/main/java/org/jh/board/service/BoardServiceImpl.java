package org.jh.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jh.board.dto.BoardDto;
import org.jh.board.entity.Board;
import org.jh.board.repository.BoardRepository;
import org.jh.board.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
    public Page<BoardDto> getBoardList(Pageable pageable) {
        log.info("BoardService.getBoardList");
        Page<Board> findBoardList = boardRepository.findAll(pageable);

        return findBoardList.map(BoardDto::new);
    }
}
