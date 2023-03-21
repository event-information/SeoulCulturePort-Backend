package com.example.seoulcultureport.dto.userDto;

import lombok.Getter;

@Getter
public class CheckIdResponseDto {

    private String message;

    public CheckIdResponseDto(String pass) {
        this.message = pass;
    }
}
