package com.example.seoulcultureport.entity;

import com.example.seoulcultureport.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter @NoArgsConstructor @Entity
public class Board extends Timestamped {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String classify;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String startDate;   // 타입 : string

    @Column(nullable = false)
    private String endDate;

    @Column(nullable = false)
    private String contents;

    private Long userid;

    public Board(BoardRequestDto boardRequestDto, User user) {
        this.title = boardRequestDto.getTitle();
        this.image = boardRequestDto.getImage();
        this.classify = boardRequestDto.getClassify();
        this.region = boardRequestDto.getRegion();
        this.location = boardRequestDto.getLocation();
        this.startDate = boardRequestDto.getStartDate();
        this.endDate = boardRequestDto.getEndDate();
        this.contents = boardRequestDto.getContents();
        this.userid = user.getId();
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.image = boardRequestDto.getImage();
        this.classify = boardRequestDto.getClassify();
        this.region = boardRequestDto.getRegion();
        this.location = boardRequestDto.getLocation();
        this.startDate = boardRequestDto.getStartDate();
        this.endDate = boardRequestDto.getEndDate();
    }
}
