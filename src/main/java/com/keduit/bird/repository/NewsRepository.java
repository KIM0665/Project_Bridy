package com.keduit.bird.repository;


import com.keduit.bird.entity.News;
import com.keduit.bird.entity.Science;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {

    // 최신 뉴스 가져오기 (작성일자 내림차순)
    Page<News> findAllByOrderByBirdysNewsRegisterDateDesc(Pageable pageable);

    // 오래된 뉴스 가저오기 (오름차순)
    Page<News> findAllByOrderByBirdysNewsRegisterDateAsc(Pageable pageable);


}