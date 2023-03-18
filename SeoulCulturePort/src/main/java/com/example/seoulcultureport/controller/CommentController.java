package com.example.seoulcultureport.controller;

import com.example.seoulcultureport.dto.CommentRequestDto;
import com.example.seoulcultureport.dto.CommentResponseDto;
import com.example.seoulcultureport.dto.MessageResponseDto;
import com.example.seoulcultureport.dto.StatusResponseDto;
import com.example.seoulcultureport.security.UserDetailsImpl;
import com.example.seoulcultureport.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments/{boardId}")
    public MessageResponseDto createComment(@PathVariable Long boardId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(boardId, commentRequestDto, userDetails.getUser());
    }

    @PutMapping("/comments/{commentId}")
    public MessageResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.updateComment(commentId, commentRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/comments/{commentId}")
    public MessageResponseDto deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.deleteComment(commentId, userDetails.getUser());
    }
}
