package com.example.seoulcultureport.dto;

import lombok.Getter;

@Getter
public class CheckIdResponseDto {

    private String message;

    public CheckIdResponseDto(String pass) {
        this.message = pass;
    }
}
