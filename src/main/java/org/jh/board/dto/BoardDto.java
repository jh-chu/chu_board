package org.jh.board.dto;

import lombok.Data;
import org.jh.board.entity.Board;

import java.time.LocalDateTime;

@Data
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;

    public BoardDto (Board board) {
        id = board.getId();
        title = board.getTitle();
        content = board.getContent();
        createDate = board.getCreateDate();
    }
}
