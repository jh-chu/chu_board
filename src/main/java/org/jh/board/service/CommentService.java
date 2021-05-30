package org.jh.board.service;

import org.jh.board.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    Page<CommentDto> getCommentList(Long boardId);
    Page<CommentDto> getCommentList(Long boardId, Pageable pageable);
}
