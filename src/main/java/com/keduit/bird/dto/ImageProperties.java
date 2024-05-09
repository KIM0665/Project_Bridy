package com.keduit.bird.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
public class ImageProperties {


    @Value("${uploadPath}")
    private String imageLocation;


}
