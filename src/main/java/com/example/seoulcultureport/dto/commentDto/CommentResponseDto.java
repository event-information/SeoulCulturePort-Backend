package com.example.seoulcultureport.dto.commentDto;


import com.example.seoulcultureport.entity.Comment;
import com.example.seoulcultureport.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private String comment;
    private String nickname;
    private Long createdat;
    private int thumbsUpCount;

    private boolean commentThumbsupStatus;

    public CommentResponseDto(Comment comment, User user) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.thumbsUpCount = comment.getCommentLikes().size();
        this.nickname = comment.getNickname();
        this.createdat = comment.getCreatedAt().toInstant(ZoneOffset.UTC).getEpochSecond();
        if(user != null) {
            this.commentThumbsupStatus = comment.commentThumbsupByUser(user.getId());
        } else {
            this.commentThumbsupStatus = false;
        }
    }

}
