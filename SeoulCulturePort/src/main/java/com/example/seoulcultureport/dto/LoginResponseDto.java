package com.example.seoulcultureport.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private int status;
    private String message;
    private String nickName;


    public LoginResponseDto(StatusEnum statusEnum, String nickName) {
        this.status = statusEnum.statusCode;
        this.message = statusEnum.msg;
        this.nickName = nickName;
    }
}
