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
public class BoardLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userid;

    @Column(nullable = false)
    private Long boardid;

    @Column(nullable = false)
    private boolean thumbsupStatus;

    public BoardLike(Long userid, Long board, boolean thumbsupStatus) {
        this.userid = userid;
        this.boardid = board;
        this.thumbsupStatus = thumbsupStatus;
    }
}
