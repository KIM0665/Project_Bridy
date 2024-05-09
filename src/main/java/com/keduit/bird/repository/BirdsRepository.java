package com.keduit.bird.repository;

import com.keduit.bird.entity.Birds;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BirdsRepository  extends JpaRepository<Birds, Long> {


    List<Birds> findByType(String type);
}
