package com.example.seoulcultureport.dto.boardDto;

import com.example.seoulcultureport.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

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
    private String createdat;

    public BoardListResponseDto(Board board) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.id = board.getId();
        this.title = board.getTitle();
        this.image = board.getImage();
        this.location = board.getLocation();
        this.region = board.getRegion();
        this.startDate = board.getStartDate();
        this.endDate = board.getEndDate();
        this.cmtCount = board.getComments().size();
        this.nickname = board.getNickname();
        this.createdat = board.getCreatedAt().format(formatter);

    }

}
