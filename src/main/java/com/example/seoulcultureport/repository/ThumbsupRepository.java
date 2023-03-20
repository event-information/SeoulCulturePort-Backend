package com.example.seoulcultureport.repository;

import com.example.seoulcultureport.entity.Thumbsup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThumbsupRepository extends JpaRepository<Thumbsup, Long> {
    Optional<Thumbsup> findByCommentIdAndUserId(Long commentId, Long userId);
}
