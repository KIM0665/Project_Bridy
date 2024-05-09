package com.keduit.bird.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "admin_birdys")
public class Birdys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "birdys_id")
    private Long id;

    @Column(name = "birdys_name", nullable = false)
    private String name;

    @Column(name = "birdys_features", nullable = false)
    private String features;

    @Column(name = "birdys_endangered")
    private Long endangered;


}
