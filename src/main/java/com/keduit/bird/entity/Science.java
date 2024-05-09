package com.keduit.bird.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;


@Table(name="admin_bridys_scienceon_news")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Science {
    @Id
    @Column(name = "birdys_scienceon_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long birdysScienceonId;

    @Column(name = "birdys_scienceon_title")
    private String birdysScienceonTitle;
    @Column(name = "birdys_scienceon_content", columnDefinition = "TEXT")
    private String birdysScienceonContent;
    @Column(name = "birdys_scienceon_source")
    private String birdysScienceonSource;
    @Column(name = "birdys_scienceon_register_date")
    private Date birdysScienceonRegisterDate;
}