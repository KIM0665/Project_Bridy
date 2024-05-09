package com.keduit.bird.repository;

import com.keduit.bird.constant.BirdGroup;
import com.keduit.bird.dto.BirdListFormDTO;

import com.keduit.bird.entity.BirdList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BirdRepository extends JpaRepository<BirdList, Long> {

    // 메인에 보여줄 페이징 된 앨범
    Page<BirdList> findByBirdGroup(BirdGroup group, Pageable pageable);

    // 카테고리 앨범에 보여줄 메서드
    List<BirdList> findByBirdGroup(BirdGroup group);


}
