package com.keduit.bird.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BirdNewsDTO {


    private String birdysNewsTitle;
    private String birdysNewsContent;
    private String birdysNewsSource;
    private LocalDateTime birdysNewsRegisterDate;
}
