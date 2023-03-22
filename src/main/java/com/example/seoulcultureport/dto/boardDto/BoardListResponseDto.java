package com.example.seoulcultureport.dto.boardDto;

import com.example.seoulcultureport.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
import java.time.ZonedDateTime;
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
    private int thumbsUpCount;

    public BoardListResponseDto(Board board) {
        // 현재 시간을 KST로 설정
        ZoneId kstZoneId = ZoneId.of("Asia/Seoul");
        ZonedDateTime now = ZonedDateTime.now(kstZoneId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(kstZoneId);
        this.id = board.getId();
        this.title = board.getTitle();
        this.image = board.getImage();
        this.location = board.getLocation();
        this.region = board.getRegion();
        this.startDate = board.getStartDate();
        this.endDate = board.getEndDate();
        this.cmtCount = board.getCmtCount();
        this.nickname = board.getNickname();
        this.thumbsUpCount = board.getThumbsups().size();
        this.createdat = board.getCreatedAt().format(formatter);

    }

}
