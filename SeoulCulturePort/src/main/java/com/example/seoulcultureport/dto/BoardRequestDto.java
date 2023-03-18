package com.example.seoulcultureport.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class BoardRequestDto{  //클라에서 요청하는 거 , id 자동 생성
    @NotNull(message = "제목을 입력하세요.")
    private String title;

    @NotNull(message = "이미지 URL을 넣어주세요.")
    private String image;

    @NotNull(message = "행사 홈페이지 URL을 넣어주세요.")
    private String pageUrl;

    @NotNull(message = "항목을 입력하세요.")
    private String classify;

    @NotNull(message = "자치구를 입력하세요.")
    private String region;

    @NotNull(message = "장소를 입력하세요.")
    private String location;

    @NotNull(message = "시작일을 입력하세요.")
    private String startDate;   // 타입 : string

    @NotNull(message = "종료일 입력하세요.")
    private String endDate;

    @NotNull(message = "내용을 입력하세요.")
    private String contents;

}
