package com.example.seoulcultureport.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PatchNickRequestDto {

    @NotBlank(message = "닉네임은 공백으로 둘 수 없습니다.")
    private String nickname;

}
