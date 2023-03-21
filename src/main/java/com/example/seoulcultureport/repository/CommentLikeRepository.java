package com.example.seoulcultureport.repository;

import com.example.seoulcultureport.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByBoardidAndCommentidAndUserid(Long boardid, Long commentid, Long userid);

    Optional<CommentLike> deleteByBoardidAndCommentidAndUserid(Long boardid, Long commentid, Long userid);
}
