package com.keduit.bird.controller;

import com.keduit.bird.dto.NewsDTO;
import com.keduit.bird.dto.ScienceDTO;
import com.keduit.bird.service.NewsService;
import com.keduit.bird.service.ScienceonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;
    private final ScienceonService scienceonService;

    @GetMapping("/news")
    public String showNews(Model model, @RequestParam(defaultValue = "0") int newPage, @RequestParam(defaultValue = "0")
                           int pastPage, @RequestParam(defaultValue = "0") int scienceonPage) {

        // 최신 뉴스 페이징 처리
        Page<NewsDTO> latestNews = newsService.findLatestNews(newPage, 3);
        model.addAttribute("newNews", latestNews);
        model.addAttribute("newPage", newPage);
        model.addAttribute("hasNextNewPage", latestNews.hasNext());


        // 과거 뉴스 페이징 처리
        Page<NewsDTO> pastNews = newsService.findPastNews(pastPage, 5);
        model.addAttribute("pastNews", pastNews);
        model.addAttribute("pastPage", pastPage);
        model.addAttribute("hasNextPastPage", pastNews.hasNext());

        
        // 과학 뉴스
        Page<ScienceDTO> scienceNews = scienceonService.findScienceNews(scienceonPage, 5);
        model.addAttribute("scienceNews", scienceNews);
        model.addAttribute("sciencePage", scienceonPage);
        model.addAttribute("hasNextSciencePage", scienceNews.hasNext());
        return "member/news";
    }
}