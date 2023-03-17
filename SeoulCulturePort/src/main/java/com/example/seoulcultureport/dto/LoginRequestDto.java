package com.example.seoulcultureport.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class LoginRequestDto {
    @NotNull(message = "아이디를 입력해 주세요")
    private String loginid;
    @NotNull(message = "비밀번호를 입력해 주세요")
    private String password;
}
