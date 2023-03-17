package com.example.seoulcultureport.service;

import com.example.seoulcultureport.dto.CommentRequestDto;
import com.example.seoulcultureport.dto.CommentResponseDto;
import com.example.seoulcultureport.entity.Comment;
import com.example.seoulcultureport.entity.User;
import com.example.seoulcultureport.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentResponseDto updateComment(Long id, CommentRequestDto commentRequestDto, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("")
        );
        if(user.getUsername().equals(comment.getUsername())) {
            comment.update(commentRequestDto);
        }
        else{
            throw new IllegalArgumentException("");
        }
        return new CommentResponseDto(comment);
    }
}
