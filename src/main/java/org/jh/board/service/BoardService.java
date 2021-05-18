package org.jh.board.service;

import org.jh.board.dto.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {

    Page<BoardDto> getBoardList(Pageable pageable);

}
