package org.jh.board.service;

import org.jh.board.dto.BoardDto;
import org.jh.board.dto.PageRequestDto;
import org.jh.board.dto.PageResultDto;
import org.jh.board.entity.Board;

public interface BoardService {

    PageResultDto<BoardDto, Board> getBoardList(PageRequestDto pageRequestDto);

}
