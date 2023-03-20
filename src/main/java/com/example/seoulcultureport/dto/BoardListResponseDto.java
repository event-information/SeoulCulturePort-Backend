package com.example.seoulcultureport.dto;

import com.example.seoulcultureport.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardListResponseDto {
    private Long id;
    private String title;
    private String image;
    private String location;
    private String region;
    private String startDate;   // 타입 : string
    private String endDate;
    private int cmtCount;
    private String nickname;
    private LocalDateTime createdat;

    public BoardListResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.image = board.getImage();
        this.location = board.getLocation();
        this.region = board.getRegion();
        this.startDate = board.getStartDate();
        this.endDate = board.getEndDate();
        this.cmtCount = board.getCmtCount();
        this.nickname = board.getNickname();
        this.createdat = board.getCreatedAt();

    }

}
