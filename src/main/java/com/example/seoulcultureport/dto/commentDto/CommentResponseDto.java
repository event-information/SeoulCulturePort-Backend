package com.example.seoulcultureport.dto.commentDto;


import com.example.seoulcultureport.entity.Comment;
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
    private String createdat;
    private int thumbsUpCount;

    private boolean commentThumbsupStatus;

    public CommentResponseDto(Comment comment, User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.thumbsUpCount = comment.getCommentLikes().size();
        this.nickname = comment.getNickname();
        this.createdat = comment.getCreatedAt().format(formatter);
        if(user != null) {
            this.commentThumbsupStatus = comment.commentThumbsupByUser(user.getId());
        } else {
            this.commentThumbsupStatus = false;
        }
    }

}
