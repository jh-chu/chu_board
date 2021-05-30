package org.jh.board.repository;

import org.jh.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom{
}
