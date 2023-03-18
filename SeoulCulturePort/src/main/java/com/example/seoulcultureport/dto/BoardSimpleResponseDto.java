package com.example.seoulcultureport.dto;

import com.example.seoulcultureport.entity.Board;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @Setter
public class BoardSimpleResponseDto {
    private Long id;
    private String title;
    private String image;
    private String contents;

    public BoardSimpleResponseDto (Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.image = board.getImage();
        this.contents = board.getContents();
    }

}
