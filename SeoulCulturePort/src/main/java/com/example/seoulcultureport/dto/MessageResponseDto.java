package com.example.seoulcultureport.dto;

import lombok.Getter;

@Getter
public class MessageResponseDto {

    private int status;
    private String message;

    public void MessageResponse(StatusEnum statusEnum) {
        this.status = statusEnum.statusCode;
        this.message = statusEnum.msg;
    }
}
