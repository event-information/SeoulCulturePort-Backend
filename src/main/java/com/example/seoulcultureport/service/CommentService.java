package com.example.seoulcultureport.service;

import com.example.seoulcultureport.dto.CommentRequestDto;

import com.example.seoulcultureport.dto.MessageResponseDto;
import com.example.seoulcultureport.dto.StatusEnum;
import com.example.seoulcultureport.entity.Board;
import com.example.seoulcultureport.entity.Comment;
import com.example.seoulcultureport.entity.User;
import com.example.seoulcultureport.exception.ApiException;
import com.example.seoulcultureport.exception.ExceptionEnum;
import com.example.seoulcultureport.repository.BoardRepository;
import com.example.seoulcultureport.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
  
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    public MessageResponseDto createComment(Long id, CommentRequestDto req, User user) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_POST_ALL)
        );

        Comment comment = commentRepository.saveAndFlush(new Comment(req, board, user));

        return new MessageResponseDto(StatusEnum.OK);
    }

    @Transactional
    public MessageResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_COMMENT_ALL)
        );
        if(user.getId().equals(comment.getUser().getId())) {
            comment.update(commentRequestDto);
        }
        else{
            throw new ApiException(ExceptionEnum.NOT_FOUND_COMMENT);
        }
        return new MessageResponseDto(StatusEnum.OK);

    }
    
    @Transactional
    public MessageResponseDto deleteComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_FOUND_COMMENT_ALL)
        );

        if (user.getId().equals(comment.getUser().getId())) {
            commentRepository.deleteById(commentId);
            return new MessageResponseDto(StatusEnum.OK);
        } else {
            throw new ApiException(ExceptionEnum.NOT_FOUND_COMMENT);
        }
    }
}
