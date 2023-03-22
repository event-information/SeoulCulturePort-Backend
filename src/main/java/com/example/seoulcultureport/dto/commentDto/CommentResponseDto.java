package com.example.seoulcultureport.dto.commentDto;


import com.example.seoulcultureport.entity.Board;
import com.example.seoulcultureport.entity.Comment;
import com.example.seoulcultureport.entity.CommentLike;
import com.example.seoulcultureport.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private String comment;
    private String nickname;
    private int thumbsUpCount;
    private String createdat;

    public CommentResponseDto(Comment comment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.nickname = comment.getNickname();
        this.thumbsUpCount = comment.getCommentLikeList().size();
        this.createdat = comment.getCreatedAt().format(formatter);

    }

}
