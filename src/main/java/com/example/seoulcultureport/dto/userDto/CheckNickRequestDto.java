package com.example.seoulcultureport.dto.userDto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CheckNickRequestDto {

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;
}
