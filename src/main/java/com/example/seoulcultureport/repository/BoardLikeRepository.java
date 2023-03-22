package com.example.seoulcultureport.repository;

import com.example.seoulcultureport.entity.Board;
import com.example.seoulcultureport.entity.BoardLike;
import com.example.seoulcultureport.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    Optional<BoardLike> findByBoardAndUser(Board board, User user);
    Optional<BoardLike> deleteByBoardAndUser(Board board, User user);
}
