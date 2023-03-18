package com.example.seoulcultureport.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class LoginRequestDto {

    @NotBlank(message = "아이디를 입력해 주세요")
    private String loginid;
    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String password;
}
