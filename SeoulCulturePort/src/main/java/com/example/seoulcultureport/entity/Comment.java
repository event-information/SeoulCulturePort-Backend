package com.example.seoulcultureport.entity;

import com.example.seoulcultureport.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "Board_ID", nullable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_ID")
    private User user;

    public Comment(CommentRequestDto commentRequestDto, User user) {
        this.comment = commentRequestDto.getComment();
        this.user = user;
    }
}
