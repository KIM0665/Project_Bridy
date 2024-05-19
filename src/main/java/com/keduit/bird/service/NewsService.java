package com.keduit.bird.service;

import com.keduit.bird.dto.NewsDTO;
import com.keduit.bird.entity.News;
import com.keduit.bird.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public List<NewsDTO> findAllNews() {
        // 모든 뉴스 정보 가져오기
        return newsRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    // 페이징 처리된 최신 뉴스 정보 가져오기
    public Page<NewsDTO> findLatestNews(int page, int size){

        return newsRepository.findAllByOrderByBirdysNewsRegisterDateDesc(PageRequest.of(page,size)).map(this::convertEntityToDTO);
    }

    // 페이징 처리된 과거 뉴스 정보 가져오기
    public Page<NewsDTO> findPastNews(int page, int size){

        return newsRepository.findAllByOrderByBirdysNewsRegisterDateAsc(PageRequest.of(page,size)).map(this::convertEntityToDTO);
    }

    public NewsDTO convertEntityToDTO(News news) {
        // 가져온 데이터를 DTO에 있는 필드을 저장한 후 DTO를 리턴
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setBirdysNewsId(news.getBirdysNewsId()); // Id
        newsDTO.setBirdysNewsTitle(news.getBirdysNewsTitle()); // 뉴스 제목
        newsDTO.setBirdysNewsContent(news.getBirdysNewsContent()); // 뉴스 내용
        newsDTO.setBirdysNewsSource(news.getBirdysNewsSource()); // 뉴스 URL
        newsDTO.setBirdysNewsRegisterDate(news.getBirdysNewsRegisterDate()); // 뉴스 등록 일자
        return newsDTO;
    }
}