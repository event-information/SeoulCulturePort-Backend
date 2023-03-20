package com.example.seoulcultureport.dto;


import com.example.seoulcultureport.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private String comment;
    private String nickname;
    private String createdat;

    private int thumbsUpCount;

    public CommentResponseDto(Comment comment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.thumbsUpCount = comment.getThumbsups().size();
        this.nickname = comment.getNickname();
        this.createdat = comment.getCreatedAt().format(formatter);
    }

}
