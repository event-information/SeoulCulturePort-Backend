package com.example.seoulcultureport.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter @NoArgsConstructor @Entity
public class Board extends Timestamped {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    //생성자, 업로드메소드 추가

}
