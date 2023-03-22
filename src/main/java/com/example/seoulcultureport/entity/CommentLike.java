package com.example.seoulcultureport.entity;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    public CommentLike(User user, Board board, Comment comment, boolean thumbsupStatus) {
        this.userid = user.getId();
        this.user = user;
        this.boardid = board.getId();
        this.board = board;
        this.commentid = comment.getId();
        this.comment = comment;
        this.thumbsupStatus = thumbsupStatus;
    }
}
