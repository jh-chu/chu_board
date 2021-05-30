package org.jh.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jh.board.dto.BoardDto;
import org.jh.board.dto.RequestCondition;
import org.jh.board.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    private final BoardService boardService;

//    @GetMapping("/board")
//    public String boardList(PageRequestDto requestDto, Model model,Sort sort) {
//
//        log.info("BoardController..." + requestDto);
//
////        Pageable pageable;
////        if(sort != null) {
////            log.info("sort는 null이 아님" + sort);
////            pageable = requestDto.getPageable(sort);
////        }
////        else {
////            log.info("sort는 null임 ㅋㅋ~");
////            pageable = requestDto.getPageable(Sort.by("createDate").descending());
////        }
//
//        log.info(sort);
//        Page<BoardDto> result = boardService.searchBoardList(
//                requestDto.getType(), requestDto.getKeyword(),requestDto.getPageable(sort));
//
//        model.addAttribute("result", result);
//
//        return "board1";
//
//    }

    @GetMapping("/board")
    @ResponseBody
    public Page<BoardDto> getBoardList(RequestCondition condition, Pageable pageable) {

        log.info("boardController..search");
        log.info("condition : " + condition);
        log.info("before pageable : " + pageable);

        pageable = RequestCondition.chkAndConvertPageable(pageable);

        log.info("after pageable : " + pageable);

        return boardService.getBoardList(condition.getType(), condition.getKeyword(),pageable);
    }



    @GetMapping("/board/{boardId}")
    @ResponseBody
    public BoardDto readBoard(RequestCondition condition, Pageable pageable,
                            @PathVariable("boardId") Long boardId,
                            Model model) {

        BoardDto dto = boardService.getBoardWithComments(boardId);

        return dto;
//        model.addAttribute("result",dto);

//        return "board/read";
    }

    @GetMapping("/reg")
    public String regPage() {

        return "board/reg";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
