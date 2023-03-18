package com.example.seoulcultureport.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
public class CheckIdRequestDto {

    @NotBlank(message = "아이디를 써주세요.")
    @Size(min = 4, max = 10, message = "아이디는 최소 4자 이상, 10자 이하의 숫자또는 알파벳 소문자를 입력하세요")
    @Pattern(regexp = "^[a-z0-9]*$", message = "아이디는 알파벳 소문자(a~z), 숫자(0~9)만 입력 가능합니다.")
    private String loginid;
}
