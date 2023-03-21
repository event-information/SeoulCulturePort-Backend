package com.example.seoulcultureport.entity;

import com.example.seoulcultureport.dto.ThumbsupStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userid;

    @Column(nullable = false)
    private Long boardid;

    @Column(nullable = false)
    private Long commentid;

    @Column(nullable = false)
    private boolean thumbsupStatus;

    public CommentLike(Long id, Long boardId, Long commentId, boolean thumbsupStatus) {
        this.userid = id;
        this.boardid = boardId;
        this.commentid = commentId;
        this.thumbsupStatus = thumbsupStatus;
    }
}
