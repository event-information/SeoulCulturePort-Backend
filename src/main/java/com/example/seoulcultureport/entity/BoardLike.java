package com.example.seoulcultureport.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class BoardLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userid;

    private Long boardid;

    public BoardLike(Long id, Board board) {
        this.userid = id;
        this.boardid = board.getId();
    }
}
