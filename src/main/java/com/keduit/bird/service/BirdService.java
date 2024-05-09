package com.keduit.bird.service;

import com.keduit.bird.entity.Birds;
import com.keduit.bird.repository.BirdsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BirdService {

    private final BirdsRepository birdsRepository;


    public List<Birds> showBirds(String type) {
        List<Birds> birds = type == null ? birdsRepository.findAll() : birdsRepository.findByType(type);

        return birds;
    }

}
