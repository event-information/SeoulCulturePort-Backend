package com.example.seoulcultureport.dto.boardDto;

import com.example.seoulcultureport.entity.Board;
import lombok.Getter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class BoardSimpleResponseDto {
    private Long id;
    private String title;
    private String image;
    private String contents;
    private int cmtCount;
    private String nickname;
    private int thumbsUpCount;
    private String createdat;

    public BoardSimpleResponseDto(Board board) {
        // 현재 시간을 KST로 설정
        ZoneId kstZoneId = ZoneId.of("Asia/Seoul");
        ZonedDateTime now = ZonedDateTime.now(kstZoneId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(kstZoneId);
        this.id = board.getId();
        this.title = board.getTitle();
        this.image = board.getImage();
        this.contents = board.getContents();
        this.cmtCount = board.getCmtCount();
        this.nickname = board.getNickname();
        this.thumbsUpCount = board.getThumbsups().size();
        this.createdat = board.getCreatedAt().format(formatter);

    }

}
