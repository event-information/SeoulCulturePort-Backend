package com.example.seoulcultureport.dto;

import com.example.seoulcultureport.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardSimpleResponseDto {
    private Long id;
    private String title;
    private String image;
    private String contents;
    private int cmtCount;
    private String nickname;
    private LocalDateTime createdat;

    public BoardSimpleResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.image = board.getImage();
        this.contents = board.getContents();
        this.cmtCount = board.getCmtCount();
        this.nickname = board.getNickname();
        this.createdat = board.getCreatedAt();

    }

}
