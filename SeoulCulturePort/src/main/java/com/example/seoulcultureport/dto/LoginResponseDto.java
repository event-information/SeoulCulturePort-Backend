package com.example.seoulcultureport.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private int status;
    private String message;
    private String nickName;


    public LoginResponseDto(StatusEnum statusEnum, String username) {
        this.status = statusEnum.statusCode;
        this.message = statusEnum.msg;
        this.nickName = username;
    }
}
