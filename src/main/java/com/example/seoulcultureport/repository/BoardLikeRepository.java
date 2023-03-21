package com.example.seoulcultureport.repository;

import com.example.seoulcultureport.entity.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    Optional<BoardLike> findByBoardidAndUserid(Long id, Long userid);

    Optional<BoardLike> deleteByBoardidAndUserid(Long id, Long Userid);
}
