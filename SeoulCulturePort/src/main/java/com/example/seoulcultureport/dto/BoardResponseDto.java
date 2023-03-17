package com.example.seoulcultureport.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class BoardResponseDto {
        private int statusCode;
        private String msg;

    public BoardResponseDto(int statusCode, String msg) {  //매개변수 있는 생성자 (controller 단에서 필요)
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
