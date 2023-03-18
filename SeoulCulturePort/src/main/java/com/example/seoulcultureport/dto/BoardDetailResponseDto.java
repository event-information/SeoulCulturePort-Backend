package com.example.seoulcultureport.dto;

import com.example.seoulcultureport.entity.Board;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BoardDetailResponseDto {
    private Long boardId;
    private String title;
    private String image;
    private String classify;
    private String region;
    private String location;
    private String startDate;   // 타입 : string
    private String endDate;
    private String contents;

    public BoardDetailResponseDto(Board board) {
        this.boardId = board.getId();
        this.title = board.getTitle();
        this.image = board.getImage();
        this.classify = board.getClassify();
        this.region = board.getRegion();
        this.location = board.getLocation();
        this.startDate = board.getStartDate();
        this.endDate = board.getEndDate();
        this.contents = board.getContents();
    }

}
//{
//    id: long,
//    title : string,
//    image : string,
//    classify : string,
//    region : string,
//    location: string,
//    startDate : string,
//    endDate : string,
//    contents : string,
//    }