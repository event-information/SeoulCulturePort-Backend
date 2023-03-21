package com.example.seoulcultureport.dto.userDto;

import lombok.Getter;

@Getter
public class CheckNickResponseDto {

    private String message;

    public CheckNickResponseDto(String pass) {
        this.message = pass;
    }
}
