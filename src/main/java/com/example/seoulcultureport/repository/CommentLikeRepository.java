package com.example.seoulcultureport.repository;

import com.example.seoulcultureport.entity.BoardLike;
import com.example.seoulcultureport.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

    Optional<CommentLike> findByUseridAndBoardidAndCommentid(Long userid, Long boardid, Long commentid);
    Optional<CommentLike> deleteByUseridAndBoardidAndCommentid(Long userid, Long boardid, Long commentid);

}