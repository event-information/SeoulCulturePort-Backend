package com.example.seoulcultureport.dto.boardDto;

import com.example.seoulcultureport.entity.Board;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class BoardSimpleResponseDto {
    private Long id;
    private String title;
    private String image;
    private String contents;
    private int cmtCount;
    private String nickname;
    private String createdat;

    public BoardSimpleResponseDto(Board board) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.id = board.getId();
        this.title = board.getTitle();
        this.image = board.getImage();
        this.contents = board.getContents();
        this.cmtCount = board.getCmtCount();
        this.nickname = board.getNickname();
        this.createdat = board.getCreatedAt().format(formatter);

    }

}
