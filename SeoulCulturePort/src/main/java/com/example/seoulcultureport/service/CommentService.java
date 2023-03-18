package com.example.seoulcultureport.service;

import com.example.seoulcultureport.dto.StatusResponseDto;
import com.example.seoulcultureport.entity.User;
import com.example.seoulcultureport.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.stream.events.Comment;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public MessageResponseDto deleteComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("")
        );

        if (user.getUsername().equals(comment.getUsername())) {
            commentRepository.deleteById(id);
            return new MessageResponseDto(StatusEnum.OK);
        } else {
            throw new IllegalArgumentException("");
        }
    }
}
