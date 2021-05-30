package org.jh.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jh.board.entity.Board;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createDate;

    private Long commentCount;
    private String writer;

    private Page<CommentDto> comments;

    public BoardDto (Board board) {
        id = board.getId();
        title = board.getTitle();
        content = board.getContent();
        createDate = board.getCreateDate();
    }

    // List용 Dto 생성자 (id, 제목, 작성자, 작성시간, 댓글갯수)
    public BoardDto (Long id, String title, String writer, LocalDateTime createDate, Long commentCount) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.createDate = createDate;
        this.commentCount = commentCount;
    }

}
