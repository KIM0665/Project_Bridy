package com.keduit.bird.dto;

import lombok.Data;

import java.util.Date;


@Data
public class NewsDTO {
    private Long birdysNewsId;
    private String birdysNewsTitle;
    private String birdysNewsContent;
    private String birdysNewsSource;
    private Date birdysNewsRegisterDate;
}