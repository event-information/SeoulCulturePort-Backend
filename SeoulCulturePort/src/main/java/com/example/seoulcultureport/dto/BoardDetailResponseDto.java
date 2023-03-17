package com.example.seoulcultureport.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BoardDetailResponseDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

}
//{
//    id: long,
//    title : string,
//    image : string,
//    classify : string,
//    region : string,
//    location: string,
//    startDate : string,
//    endDate : string,
//    contents : string,
//    }