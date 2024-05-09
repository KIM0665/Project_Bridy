package com.keduit.bird.repository;

import com.keduit.bird.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Profile findByMemberMemberEmail(String memberEmail);

}
