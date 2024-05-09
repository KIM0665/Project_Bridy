package com.keduit.bird.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="profile_id")
    private Long id;

    private String profileOrigin;
    private String profileName;
    private String profileUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    public Profile() {}
    public Profile(Member member) {
        this.member = member;
    }

    //이미지 업데이트
    public void updateProfile(String profileOrigin, String profileName, String profileUrl){
        this.profileOrigin = profileOrigin;
        this.profileName = profileName;
        this.profileUrl = profileUrl;
    }
}
