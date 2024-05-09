package com.keduit.bird.service;

import com.keduit.bird.dto.Bird;
import com.keduit.bird.dto.BirdStatisticsDTO;
import com.keduit.bird.repository.BirdStatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class BirdStatisticsService {

    private final BirdStatisticsRepository birdStatisticsRepository;




    // 새의 총 개체수
    public Map<String, Map<String, Long>> getTotalCountByBirdName() {

        // 저장할 Map
//        Map<String, Integer> totalCountByBirdName = new HashMap<>();
        Map<String, Map<String, Long>> birdBirdNameCity = new HashMap<>();


        // DB에 저장된 새의 모든 이름을 갖고옴
        List<String> birdNames = birdStatisticsRepository.findAllBirdNames();


        for (String birdName : birdNames) {
            // 각 새 이름에 대한 새로운 cityMap을 생성합니다
            Map<String, Long> cityMap = new HashMap<>();

            List<BirdStatisticsDTO> cityCounts = birdStatisticsRepository.findCityCountsByNames(birdName);

            for(BirdStatisticsDTO birdStatisticsDTO : cityCounts){
                cityMap.put(birdStatisticsDTO.getDosi(), birdStatisticsDTO.getCount());
            }

            birdBirdNameCity.put(birdName, cityMap);
        }


        return birdBirdNameCity;
    }

    public Map<String, Long> getTotalCountBirdName(int page, int size) {
        Map<String, Long> totalCountByBirdName = new HashMap<>();


        // 모든 새 종류 조회
        List<String> birdNames = birdStatisticsRepository.findAllBirdNames(PageRequest.of(page,size));

        // 각 새 종류별 개체수 합산
        for (String birdName : birdNames) {
            Long totalCount = birdStatisticsRepository.findTotalCountByName(birdName);
            totalCountByBirdName.put(birdName, totalCount);
        }

        return totalCountByBirdName;
    }

    // 새의 도시별 개체수
    public Map<String, Long> getTotalBirdName() {

        // 저장할 Map
//        Map<String, Integer> totalCountByBirdName = new HashMap<>();
        Map<String, Long> birdBirdNameCity = new HashMap<>();


        // DB에 저장된 새의 모든 이름을 갖고옴
        List<String> birdNames = birdStatisticsRepository.findAllBirdNames();


        for (String birdName : birdNames) {

            List<BirdStatisticsDTO> cityCounts = birdStatisticsRepository.findCityCountsByNames(birdName);

            for(BirdStatisticsDTO birdStatisticsDTO : cityCounts){
                birdBirdNameCity.put(birdStatisticsDTO.getDosi(), birdStatisticsDTO.getCount());
            }

        }


        return birdBirdNameCity;
    }


    // 페이징 처리

    public Page<Map.Entry<String, Map<String, Long>>> getBirdPage(Pageable pageable){

        Map<String, Map<String, Long>> birdNameCity = getTotalCountByBirdName();

        List<Map.Entry<String, Map<String, Long>>> birdStatisticsDTOList  = new ArrayList<>(birdNameCity.entrySet());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), birdStatisticsDTOList.size());

        List<Map.Entry<String, Map<String, Long>>> sublist = birdStatisticsDTOList.subList(start, end);


        return new PageImpl<>(sublist, pageable, birdNameCity.size());
    }

}
