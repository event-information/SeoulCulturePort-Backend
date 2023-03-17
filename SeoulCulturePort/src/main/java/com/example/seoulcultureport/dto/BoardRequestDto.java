package com.example.seoulcultureport.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class BoardRequestDto{  //클라에서 요청하는 거 , id 자동 생성
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String classify;
    @Column(nullable = false)
    private String region;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String startDate;   // 타입 : string
    @Column(nullable = false)
    private String endDate;
    @Column(nullable = false)
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
