package com.example.seoulcultureport.dto;

import com.example.seoulcultureport.entity.Board;
import lombok.Getter;

@Getter
public class BoardSimpleResponseDto {
    private Long id;
    private String title;
    private String image;
    private String contents;

    public BoardSimpleResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.image = board.getImage();
        this.contents = board.getContents();
    }

}
