package com.example.seoulcultureport.dto;

import com.example.seoulcultureport.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Getter @NoArgsConstructor
public class BoardListResponseDto {
    private Long id;
    private String title;
    private String image;
    private String location;
    private String startDate;   // 타입 : string
    private String endDate;

    public BoardListResponseDto(Board board) {
      this.id = board.getId();
      this.title = board.getTitle();
      this.image = board.getImage();
      this.location = board.getLocation();
      this.startDate = board.getStartDate();
      this.endDate = board.getEndDate();

    }


}



//{
//    id : long,
//    title: string,
//    image : string,
//    location : string,
//    startDate : string,
//    endDate : string,
//    }
