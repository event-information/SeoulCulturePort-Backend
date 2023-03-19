package com.example.seoulcultureport.dto;

public enum StatusEnum {

    OK(200, "success");

    int statusCode;
    String msg;

    StatusEnum(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
