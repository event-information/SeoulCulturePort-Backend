package com.example.seoulcultureport.dto;

import com.example.seoulcultureport.entity.Comment;

public class CommentResponseDto {

    private Long id;
    private String nickname;
    private String comment;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.nickname = comment.getNickname();
        this.comment = comment.getComment();
    }
}
