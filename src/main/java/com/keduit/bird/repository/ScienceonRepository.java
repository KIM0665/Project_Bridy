package com.keduit.bird.repository;

import com.keduit.bird.entity.Science;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScienceonRepository extends JpaRepository<Science, Long> {

    // 과학 뉴스 가져오기
    Page<Science> findAllByOrderByBirdysScienceonRegisterDateDesc(Pageable pageable);
}
