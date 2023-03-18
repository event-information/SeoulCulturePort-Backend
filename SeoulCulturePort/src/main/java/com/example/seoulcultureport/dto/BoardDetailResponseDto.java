package com.example.seoulcultureport.dto;

import com.example.seoulcultureport.entity.Board;
import lombok.Getter;

@Getter
public class BoardDetailResponseDto {
    private Long id;
    private String title;
    private String image;
    private String pageUrl;
    private String classify;
    private String region;
    private String location;
    private String startDate;   // 타입 : string
    private String endDate;
    private String contents;

    public BoardDetailResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.image = board.getImage();
        this.pageUrl = board.getPageUrl();
        this.classify = board.getClassify();
        this.region = board.getRegion();
        this.location = board.getLocation();
        this.startDate = board.getStartDate();
        this.endDate = board.getEndDate();
        this.contents = board.getContents();
    }

}
