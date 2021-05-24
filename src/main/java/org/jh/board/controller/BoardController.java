package org.jh.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jh.board.dto.BoardDto;
import org.jh.board.dto.PageRequestDto;
import org.jh.board.dto.PageResultDto;
import org.jh.board.entity.Board;
import org.jh.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public String boardList(PageRequestDto pageRequestDto, Model model) {

        log.info("BoardController..." + pageRequestDto);

        model.addAttribute("result", boardService.getBoardList(pageRequestDto));

        return "board1";

    }

    @GetMapping("/reg")
    public String regPage() {

        return "board/reg";
    }


}
