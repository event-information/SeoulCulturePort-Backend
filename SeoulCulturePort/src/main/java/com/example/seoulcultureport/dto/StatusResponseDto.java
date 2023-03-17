package com.example.seoulcultureport.dto;

public class StatusResponseDto {

    private int statusCode;
    private String msg;

    public StatusResponseDto(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }

    public void setHttpStatus(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
