package com.example.seoulcultureport.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardRequestDto{  //클라에서 요청하는 거 , id 자동 생성
    private String title;
    private String image;
    private String classify;
    private String region;
    private String location;
    private String startDate;   // 타입 : string
    private String endDate;
    private String contents;
   //{
    //    title : string,
    //    image : string,
    //    classify : string,
    //    region : string,
    //    location: string,
    //    startDate : string,
    //    endDate : string,
    //    contents : string,
    //    }



    //    public PostRequestDto(String title, List<String> tags, String content) {
    //        this.title = title;
    //        this.tags = tags;
    //        this.content = content;
    //    }
}
