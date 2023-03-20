package com.example.seoulcultureport.dto;


import com.example.seoulcultureport.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private String comment;

    private int thumbsUpCount;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.thumbsUpCount = comment.getThumbsups().size();
    }

}
