package com.example.seoulcultureport.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThumbsupResponseDto {

    private int statusCode;
    private String msg;
    private Long thumbsupId;
    private ThumbsupStatus thumbsupStatus;

    public ThumbsupResponseDto(StatusEnum statusEnum, Long thumbsupId, ThumbsupStatus thumbsupStatus) {
        this.thumbsupId = thumbsupId;
        this.statusCode = statusEnum.statusCode;
        this.msg = statusEnum.msg;
        this.thumbsupStatus = thumbsupStatus;
    }

}
