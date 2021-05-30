package org.jh.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jh.board.dto.CommentDto;
import org.jh.board.entity.Comment;
import org.jh.board.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Override
    public Page<CommentDto> getCommentList(Long boardId) {

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("createDate").descending());

        return getCommentList(boardId, pageRequest);

    }

    @Override
    public Page<CommentDto> getCommentList(Long boardId, Pageable pageable) {

        Page<Comment> findComment = commentRepository.findByBoardId(boardId, pageable);

        Page<CommentDto> result = findComment.map(CommentDto::new);

        return result;
    }


}
