package com.keduit.bird.service;

import com.keduit.bird.dto.ScienceDTO;
import com.keduit.bird.entity.Science;
import com.keduit.bird.repository.NewsRepository;
import com.keduit.bird.repository.ScienceonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ScienceonService {

    private final ScienceonRepository scienceonRepository;


    public Page<ScienceDTO> findScienceNews(int page, int size) {
        return scienceonRepository.findAllByOrderByBirdysScienceonRegisterDateDesc(PageRequest.of(page,size)).map(this::scienceonEntityToDTO);
    }

    public ScienceDTO scienceonEntityToDTO(Science science) {
        ScienceDTO scienceDTO = new ScienceDTO();
        scienceDTO.setBirdysScienceonId(science.getBirdysScienceonId());
        scienceDTO.setBirdysScienceonTitle(science.getBirdysScienceonTitle());
        scienceDTO.setBirdysScienceonContent(science.getBirdysScienceonContent());
        scienceDTO.setBirdysScienceonSource(science.getBirdysScienceonSource());
        scienceDTO.setBirdysScienceonRegisterDate(science.getBirdysScienceonRegisterDate());
        return scienceDTO;
    }

}
