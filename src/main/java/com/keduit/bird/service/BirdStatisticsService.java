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




    // 새의 총 개체수와 각 도시별 개체 수를 반환하는 메서드
    public Map<String, Map<String, Long>> getTotalCountByBirdName() {

        // 저장할 Map
        Map<String, Map<String, Long>> birdNameCity = new HashMap<>();
        // DB에 저장된 새의 모든 이름을 갖고옴
        List<String> birdNames = birdStatisticsRepository.findAllBirdNames();

        // 각 새 이름에 대한 개체 수를 계산하여 Map에 저장
        for (String birdName : birdNames) {
            // 각 새 이름에 대한 도시별 총 개체 수를 저장할 cityMap를 생성
            Map<String, Long> cityMap = new HashMap<>();

            // 새 이름에 대한 각 도시별 개체 수를 조회한 후 cityCounts에 저장
            List<BirdStatisticsDTO> cityCounts = birdStatisticsRepository.findCityCountsByNames(birdName);

            // 리스트 형태인 cityCounts를 1개씩 꺼내와서 cityMap에 저장
            for(BirdStatisticsDTO birdStatisticsDTO : cityCounts){
                cityMap.put(birdStatisticsDTO.getDosi(), birdStatisticsDTO.getCount());
            }
            // 새 이름과 새 이름에 대한 도시별 총 개체 수를 birdNameCity에 저장
            birdNameCity.put(birdName, cityMap);
        }
        // 담겨진 변수를 반환
        return birdNameCity;
    }

    // 새의 총 개체 수를 페이지별로 반환하는 메서드
    public Map<String, Long> getTotalCountBirdName(int page, int size) {
        Map<String, Long> totalCountByBirdName = new HashMap<>();


        // 모든 새 종류 조회, 페이징 처리
        List<String> birdNames = birdStatisticsRepository.findAllBirdNames(PageRequest.of(page,size));

        // 각 새 종류별 개체수 합산
        for (String birdName : birdNames) {
            // birdNames와 일치하는 새 이름으로 총 개체 수를 구함
            Long totalCount = birdStatisticsRepository.findTotalCountByName(birdName);

            // 새 이름과 총 개체수를
            totalCountByBirdName.put(birdName, totalCount);
        }
        // 담겨진 Map를 반환
        return totalCountByBirdName;
    }

    // 새의 도시별 개체 수를 반화하는 메서드
    public Map<String, Long> getTotalBirdName() {

        // 저장할 Map
        Map<String, Long> birdBirdNameCity = new HashMap<>();

        // DB에 저장된 새의 모든 이름을 갖고옴
        List<String> birdNames = birdStatisticsRepository.findAllBirdNames();

        // 각 새 이름에 대한 도시별 개체 수를 계산하기 위함
        for (String birdName : birdNames) {
            // 새 이름에 대한 각 도시별 개체 수를 조회하여 리스트에 저장
            List<BirdStatisticsDTO> cityCounts = birdStatisticsRepository.findCityCountsByNames(birdName);

            for(BirdStatisticsDTO birdStatisticsDTO : cityCounts){
                birdBirdNameCity.put(birdStatisticsDTO.getDosi(), birdStatisticsDTO.getCount());
            }

        }
        return birdBirdNameCity;
    }


    // 페이징 처리된 새의 통계 데이터를 반환하는 메서드
    public Page<Map.Entry<String, Map<String, Long>>> getBirdPage(Pageable pageable){

        // 새의 총 개체 수와 각 도시별 개체 수를 가져옴
        Map<String, Map<String, Long>> birdNameCity = getTotalCountByBirdName();

        // Map을 List로 변환하여 페이지 처리
        List<Map.Entry<String, Map<String, Long>>> birdStatisticsDTOList  = new ArrayList<>(birdNameCity.entrySet());

        // 페이지 번호와 페이지 크기에 따라 해당 페이지에 대한 데이터를 추출
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), birdStatisticsDTOList.size());

        List<Map.Entry<String, Map<String, Long>>> sublist = birdStatisticsDTOList.subList(start, end);


        return new PageImpl<>(sublist, pageable, birdNameCity.size());
    }

}
