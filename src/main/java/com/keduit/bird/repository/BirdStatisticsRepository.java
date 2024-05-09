package com.keduit.bird.repository;

import com.keduit.bird.dto.BirdStatisticsDTO;
import com.keduit.bird.entity.Birdy_statistics;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface BirdStatisticsRepository extends JpaRepository<Birdy_statistics, Long> {


    @Query("select sum(b.count) from Birdy_statistics b where b.name = :name")
    Long findTotalCountByName(@Param("name") String name);

    // 새 이름 중복 없이 1개의 이름 출력
    @Query("select distinct b.name from Birdy_statistics b")
    List<String> findAllBirdNames(Pageable pageable);
    @Query("select distinct b.name from Birdy_statistics b")
    List<String> findAllBirdNames();

    @Query("select new com.keduit.bird.dto.BirdStatisticsDTO(b.dosi, sum(b.count)) " +
            "from Birdy_statistics b where b.name = :name group by b.dosi")
    List<BirdStatisticsDTO> findCityCountsByNames(String name);
}
