package com.example.seoulcultureport.dto;

public enum StatusEnum {

    OK(200, "success"),
    LIKEOK(200, "좋아요 누르기 성공"),
    LIKE_CANCLE(200, "좋아요 취소 성공");

    int statusCode;
    String msg;

    StatusEnum(int statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
