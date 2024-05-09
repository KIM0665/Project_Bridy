package com.keduit.bird.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Birdy_statistics {


    @Id
    @Column(name = "birdys_locations_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(name = "bird_name")
    private String name; // 새의 이름

    @Column(name = "bird_date")
    private String date; // 년도 2019

    @Column(name = "bird_dosi")
    private String  dosi; // 도시명

    @Column(name = "count")
    private int count; // 개체수

}
