package com.example.seoulcultureport.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class BoardRequestDto {
    @NotBlank(message = "제목을 입력하세요.")
    private String title;

    @NotBlank(message = "이미지 URL을 넣어주세요")
    private String image;

    @NotBlank(message = "행사 홈페이지 URL을 넣어주세요.")
    private String pageUrl;

    @NotBlank(message = "항목을 입력하세요.")
    private String classify;

    @NotBlank(message = "자치구를 입력하세요.")
    private String region;

    @NotBlank(message = "장소를 입력하세요.")
    private String location;

    @NotBlank(message = "시작일을 입력하세요.")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "날짜 형식을 yyyy-MM-dd로 입력하세요.")
    private String startDate;   // 타입 : string

    @NotBlank(message = "종료일 입력하세요.")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "날짜 형식은 yyyy-MM-dd로 입력하세요.")
    private String endDate;

    @NotBlank(message = "내용을 입력하세요.")
    private String contents;

}
