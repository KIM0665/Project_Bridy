package com.keduit.bird.entity;

import com.keduit.bird.constant.BirdGroup;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class BirdList {

    @Id
    @Column(name = "bird_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String birdName;

    @Column(length = 2000)
    private String birdDetail;

    @Column(length = 1000)
    private String birdImgUrl;

    @Enumerated(EnumType.STRING)
    private BirdGroup birdGroup;

    // 기본 생성자
    public BirdList() {}

    // 생성자
    public BirdList(String birdName, String birdDetail, String birdImgUrl, BirdGroup birdGroup) {
        this.birdName = birdName;
        this.birdDetail = birdDetail;
        this.birdImgUrl = birdImgUrl;
        this.birdGroup = birdGroup;
    }
}
