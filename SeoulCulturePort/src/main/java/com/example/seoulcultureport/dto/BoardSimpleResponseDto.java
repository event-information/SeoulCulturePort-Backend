package com.example.seoulcultureport.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BoardSimpleResponseDto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String contents;
}


//[{
//    id: long,
//    title:string,
//    image:string,
//    contents:string,
//    },]