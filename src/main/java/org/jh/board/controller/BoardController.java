package org.jh.board.controller;

import lombok.RequiredArgsConstructor;
import org.jh.board.dto.BoardDto;
import org.jh.board.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public Page<BoardDto> getBoard(Pageable pageable) {

        Page<BoardDto> boardList = boardService.getBoardList(pageable);

        return boardList;

    }


}
