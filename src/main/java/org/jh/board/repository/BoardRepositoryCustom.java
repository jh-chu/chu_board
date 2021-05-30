package org.jh.board.repository;

import org.jh.board.dto.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {
    Page<BoardDto> searchBoardPage(String type, String keyword, Pageable pageable);
}
