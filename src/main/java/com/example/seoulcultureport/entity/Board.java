package com.example.seoulcultureport.entity;


import com.example.seoulcultureport.dto.boardDto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String pageUrl;

    @Column(nullable = false)
    private String classify;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String startDate; // 타입 : string

    @Column(nullable = false)
    private String endDate;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Long userid; //단방향

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = true)
    private int cmtCount = 0;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @OrderBy(value = "createdAt DESC")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "board_like")
    List<BoardLike> boardLikeList = new ArrayList<>();

//    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Thumbsup> thumbsups = new ArrayList<>();

    public void plusCmtCount() {this.cmtCount ++;}
    public void minusCmtCount() {this.cmtCount --;}


    public Board(BoardRequestDto boardRequestDto, User user) {
        this.title = boardRequestDto.getTitle();
        this.image = boardRequestDto.getImage();
        this.pageUrl = boardRequestDto.getPageUrl();
        this.classify = boardRequestDto.getClassify();
        this.region = boardRequestDto.getRegion();
        this.location = boardRequestDto.getLocation();
        this.startDate = boardRequestDto.getStartDate();
        this.endDate = boardRequestDto.getEndDate();
        this.contents = boardRequestDto.getContents();
        this.userid = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.title = boardRequestDto.getTitle();
        this.image = boardRequestDto.getImage();
        this.pageUrl = boardRequestDto.getPageUrl();
        this.classify = boardRequestDto.getClassify();
        this.region = boardRequestDto.getRegion();
        this.location = boardRequestDto.getLocation();
        this.startDate = boardRequestDto.getStartDate();
        this.endDate = boardRequestDto.getEndDate();
        this.contents = boardRequestDto.getContents();
    }

    public void addThumbsup(Thumbsup thumbsup) {
        this.thumbsups.add(thumbsup);
        thumbsup.setBoard(this);
    }

    public void cancelThumbsup(Thumbsup thumbsup) {
        this.thumbsups.remove(thumbsup);
        thumbsup.setBoard(null);
    }


}
