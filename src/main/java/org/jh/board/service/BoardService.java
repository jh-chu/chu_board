package org.jh.board.service;

import org.jh.board.dto.BoardDto;
import org.jh.board.dto.RequestCondition;
import org.jh.board.dto.PageResultDto;
import org.jh.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BoardService {

    PageResultDto<BoardDto, Board> getBoardList(RequestCondition requestCondition);

    Page<BoardDto> getBoardList(String type, String keyword, Pageable pageable);

    BoardDto getBoardWithComments(Long boardId);
    BoardDto getBoardWithComments(Long boardId,Pageable commentPageable);
}
