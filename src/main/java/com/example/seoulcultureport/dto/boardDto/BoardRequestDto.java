package com.example.seoulcultureport.dto.boardDto;

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
    @Pattern(regexp = "^https?:\\/\\/.+\\.(jpe?g|png|gif)$", message = "이미지 URL 형식이 올바르지 않습니다.")
    //정규식은 http:// 또는 https://로 시작하는 URL 형식이며, .jpg, .jpeg, .png, .gif 확장자를 가진 이미지 파일만을 허용
    //유효한 이미지 파일인지 확인하기 위해서는 추가적인 검증 과정이 필요 (MIME 타입 검사)
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
