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

    private Long userid;

    private Long boardid;

    @Enumerated(EnumType.STRING)
    private ThumbsupStatus thumbsupStatus;

    public BoardLike(Long userid, Long board, ThumbsupStatus thumbsupStatus) {
        this.userid = userid;
        this.boardid = board;
        this.thumbsupStatus = thumbsupStatus;
    }
}
