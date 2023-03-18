package com.example.seoulcultureport.dto;

import com.example.seoulcultureport.entity.Board;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BoardSimpleResponseDto {
    private Long id;
    private String title;
    private String image;
    private String contents;

    public BoardSimpleResponseDto (Board board) {
        this.id = board.getUserid();
        this.title = board.getTitle();
        this.image = board.getImage();
        this.contents = board.getContents();
    }


}


//[{
//    id: long,
//    title:string,
//    image:string,
//    contents:string,
//    },]