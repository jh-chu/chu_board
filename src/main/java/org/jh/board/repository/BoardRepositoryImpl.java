package org.jh.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.jh.board.dto.BoardDto;
import org.jh.board.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import static org.jh.board.entity.QBoard.*;
import static org.jh.board.entity.QComment.*;
import static org.jh.board.entity.QMember.*;

public class BoardRepositoryImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom{

    public BoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Page<BoardDto> searchBoardPage(String type, String keyword, Pageable pageable) {

        JPQLQuery<BoardDto> query = from(board)
                .leftJoin(board.member, member)
                .leftJoin(comment).on(comment.board.eq(board))
                .groupBy(board)
                .select(Projections.constructor(
                        BoardDto.class,
                        board.id, board.title, member.nickname, board.createDate, comment.count()
                ));


        //검색 처리
        BooleanBuilder condition = new BooleanBuilder();

        if(type != null) {
            String[] typeArr = type.split("");
            for (String t : typeArr) {

                switch (t) {
                    case "t":
                        condition.and(board.title.contains(keyword));
                        break;
                    case "w":
                        condition.and(member.nickname.contains(keyword));
                        break;
                    case "c":
                        condition.and(board.content.contains(keyword));
                        break;
                }

            }
            query.where(condition);
        }

        //페이징 처리
        JPQLQuery<BoardDto> pagingQuery = getQuerydsl().applyPagination(pageable, query);


        QueryResults<BoardDto> results = pagingQuery.fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

}
