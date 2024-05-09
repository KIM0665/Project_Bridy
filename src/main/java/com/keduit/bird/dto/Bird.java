package com.keduit.bird.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Bird {

    private String name;
    private Long count;

    public Bird(String name, Long count) {
        this.name = name;
        this.count = count;
    }

}
