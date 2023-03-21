package com.example.seoulcultureport.dto.boardDto;

import com.example.seoulcultureport.dto.ThumbsupStatus;
import com.example.seoulcultureport.dto.commentDto.CommentResponseDto;
import com.example.seoulcultureport.entity.Board;
import com.example.seoulcultureport.entity.Comment;
import com.example.seoulcultureport.entity.Thumbsup;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    private int cmtCount;
    private String username;
    private String createdat;
    private String nickname;
    private int thumbsUpCount;
    private ThumbsupStatus thumbsUpStatus;

    private final List<CommentResponseDto> commentList = new ArrayList<>();

    public BoardDetailResponseDto(Board board, Thumbsup thumbsup) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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
        this.cmtCount = board.getCmtCount();
        this.username = board.getUsername();
        this.nickname = board.getNickname();
        this.createdat = board.getCreatedAt().format(formatter);
        this.thumbsUpCount = board.getThumbsups().size();
        this.thumbsUpStatus = thumbsup.getThumbsupStatus();

        for (Comment comment : board.getComments()) {
            commentList.add(new CommentResponseDto(comment));
        }
    }

}
