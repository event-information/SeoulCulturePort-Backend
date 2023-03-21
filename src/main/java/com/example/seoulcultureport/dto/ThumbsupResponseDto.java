package com.example.seoulcultureport.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThumbsupResponseDto {

    private int statusCode;
    private String msg;
    private Long thumbsupId;
    private boolean thumbsupStatus;

    public ThumbsupResponseDto(StatusEnum statusEnum, Long thumbsupId, boolean thumbsupStatus) {
        this.thumbsupId = thumbsupId;
        this.statusCode = statusEnum.statusCode;
        this.msg = statusEnum.msg;
        this.thumbsupStatus = thumbsupStatus;
    }

}
