package org.jh.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jh.board.entity.Comment;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String nickname;
    private String content;
    private LocalDateTime createDate;

    public CommentDto(Comment comment) {
        id = comment.getId();
        nickname = comment.getMember().getNickname();
        content = comment.getContent();
        createDate = comment.getCreateDate();
    }


}
