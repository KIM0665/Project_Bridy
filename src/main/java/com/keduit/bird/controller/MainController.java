package com.keduit.bird.controller;

import com.keduit.bird.constant.BirdGroup;
import com.keduit.bird.dto.*;
import com.keduit.bird.entity.BirdList;
import com.keduit.bird.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final BirdStatisticsService birdStatisticsService;
    private final BoardService boardService;
    private final NewsService newsService;
    private final ScienceonService scienceonService;
    private final BirdListService birdListService;


    @GetMapping("/")
    public String birdTest(Model model, HttpServletRequest request , @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size,
                           @RequestParam(defaultValue = "0") int scienceonPage)  {

        // 새 지역별 개체수 파악
        Map<String, Map<String, Long>> birdStatisticsDTOList = birdStatisticsService.getTotalCountByBirdName();

        Pageable pageable = PageRequest.of(page -1, size);
        Page<Map.Entry<String, Map<String, Long>>> birdPage = birdStatisticsService.getBirdPage(pageable);

        // 새 총 개체수와 새 이름 목록 가져오기
        Map<String, Long> birdList = birdStatisticsService.getTotalBirdName();
        Map<String, Long> birdCount = birdStatisticsService.getTotalCountBirdName(page, 10);

        // 최초 페이지 로드 여부를 확인하는 변수
        boolean isFirstPage = false;
        // 최초 페이지 로드인지 확인
        if (request.getParameter("page") == null) {
            isFirstPage = true;
        }


        // 게시판 리스트 출력
        List<BoardDTO> boardDTOList = boardService.findAll();

        // 멸종 위기종 기준
        BirdGroup group = BirdGroup.I;

        // intro 여부
        model.addAttribute("isFirstPage", isFirstPage);

        // 새의 통계 처리
        model.addAttribute("birdPage", birdPage); // 페이징 처리
        model.addAttribute("birdList", birdList); // 새의 종류 개체수
        model.addAttribute("birdStatisticsDTOList", birdStatisticsDTOList); // 종합본
        model.addAttribute("birdCount", birdCount);

        // 게시판 처리
        model.addAttribute("boardList",boardDTOList); // 게시판

        // 최신 뉴스 페이징 처리
        Page<NewsDTO> latestNews = newsService.findLatestNews(page, 4);
        model.addAttribute("newNews", latestNews);
        model.addAttribute("newPage", page);
        model.addAttribute("hasNextNewPage", latestNews.hasNext());

        // 과학뉴스
        Page<ScienceDTO> scienceNews = scienceonService.findScienceNews(scienceonPage, 4);
        model.addAttribute("scienceNews", scienceNews);
        model.addAttribute("sciencePage", scienceonPage);
        model.addAttribute("hasNextSciencePage", scienceNews.hasNext());


        // 새의 앨범
        Page<BirdListFormDTO> birdblog = birdListService.getBirdListByBirdGroup(group, page, 3);
        model.addAttribute("birdblog", birdblog);
        return "index";
    }
}
