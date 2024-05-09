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
        return newsRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    // 최신 뉴스 정보 가져오기
    public Page<NewsDTO> findLatestNews(int page, int size){

        return newsRepository.findAllByOrderByBirdysNewsRegisterDateDesc(PageRequest.of(page,size)).map(this::convertEntityToDTO);
    }

    // 과거 뉴스 정보 가져오기
    public Page<NewsDTO> findPastNews(int page, int size){

        return newsRepository.findAllByOrderByBirdysNewsRegisterDateAsc(PageRequest.of(page,size)).map(this::convertEntityToDTO);
    }




    public NewsDTO convertEntityToDTO(News news) {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setBirdysNewsId(news.getBirdysNewsId());
        newsDTO.setBirdysNewsTitle(news.getBirdysNewsTitle());
        newsDTO.setBirdysNewsContent(news.getBirdysNewsContent());
        newsDTO.setBirdysNewsSource(news.getBirdysNewsSource());
        newsDTO.setBirdysNewsRegisterDate(news.getBirdysNewsRegisterDate());
        return newsDTO;
    }
}