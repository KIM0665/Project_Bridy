package com.keduit.bird.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ScienceDTO {

    private Long birdysScienceonId;
    private String birdysScienceonTitle;
    private String birdysScienceonContent;
    private String birdysScienceonSource;
    private Date birdysScienceonRegisterDate;

}
