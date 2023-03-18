package com.example.seoulcultureport.dto;


import com.example.seoulcultureport.entity.Comment;

public class CommentResponseDto {

    private Long id;
    private String comment;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
    }

}
