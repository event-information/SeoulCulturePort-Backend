package com.example.seoulcultureport.service;

import com.example.seoulcultureport.dto.StatusResponseDto;
import com.example.seoulcultureport.entity.User;
import com.example.seoulcultureport.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public StatusResponseDto deleteComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("")
        );

        StatusResponseDto result = new StatusResponseDto();
        if (user.getUsername().equals(comment.getUsername())) {
            commentRepository.deleteById(id);
            result.setHttpStatus(HttpStatus.OK.value(), "댓글 삭제 성공");
            return result;
        } else {
            result.setHttpStatus(HttpStatus.BAD_REQUEST.value(), "댓글 삭제 실패");
            return result;
        }
    }
}
