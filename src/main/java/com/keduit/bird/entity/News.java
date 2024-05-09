package com.keduit.bird.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "birdys_news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long birdysNewsId;

    @Column(nullable = false)
    private String birdysNewsTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String birdysNewsContent;

    private String birdysNewsSource;

    @Temporal(TemporalType.TIMESTAMP)
    private Date birdysNewsRegisterDate;
}


