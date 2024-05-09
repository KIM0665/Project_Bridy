package com.keduit.bird.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BirdStatisticsDTO {

//    private Long id;
//    private String name; // 새의 이름
//    private String date; // 년도 2019
    private String  dosi; // 도시명
    private Long count; // 개체수
    public BirdStatisticsDTO(String dosi, long count) {
        this.dosi = dosi;
        this.count = count;
    }

}
