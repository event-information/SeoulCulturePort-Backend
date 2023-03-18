package com.example.seoulcultureport.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class BoardSimpleResponseDto {
    private Long id;
    private String title;
    private String image;
    private String contents;
}


//[{
//    id: long,
//    title:string,
//    image:string,
//    contents:string,
//    },]