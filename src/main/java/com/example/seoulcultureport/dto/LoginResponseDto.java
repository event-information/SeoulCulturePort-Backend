package com.example.seoulcultureport.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private int status;
    private String message;
    private String nickname;


    public LoginResponseDto(StatusEnum statusEnum, String nickname) {
        this.status = statusEnum.statusCode;
        this.message = statusEnum.msg;
        this.nickname = nickname;
    }
}
