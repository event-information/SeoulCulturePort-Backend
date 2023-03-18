package com.example.seoulcultureport.service;

import com.example.seoulcultureport.dto.CommentRequestDto;
import com.example.seoulcultureport.dto.CommentResponseDto;
import com.example.seoulcultureport.entity.Comment;
import com.example.seoulcultureport.entity.User;
import com.example.seoulcultureport.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public MessageResponseDto createComment(CommentRequestDto req, User user) {
        Board board = boardRepository.findById(req.getBoardID()).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );

        Comment comment = commentRepository.saveAndFlush(new Comment(req, user));

        return new MessageResponseDto(StatusEnum.OK);
    }
}
