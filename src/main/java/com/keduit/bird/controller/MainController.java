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

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
    public String birdTest(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size,
                           @RequestParam(defaultValue = "0") int scienceonPage) throws IOException {


        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Map.Entry<String, Map<String, Long>>> birdPage = birdStatisticsService.getBirdPage(pageable);

        // 새 총 개체수와 새 이름 목록 가져오기
        Map<String, Long> birdList = birdStatisticsService.getTotalBirdName();

        // 새 지역별 개체수 파악
        Map<String, Map<String, Long>> birdStatisticsDTOList = birdStatisticsService.getTotalCountByBirdName();

        // birdStatisticsDTOList를 라벨과 데이터로 변환
        List<String> birdLabels = new ArrayList<>(); // Map 키
        List<Long> birdData = new ArrayList<>(); // Map 값

        for (Map.Entry<String, Map<String, Long>> entry : birdPage.toSet()) {
            birdLabels.add(entry.getKey()); // 새의 이름을 라벨로 추가

            // 각 새의 지역별 개체수를 합산하여 데이터로 추가
            long totalCount = entry.getValue().values().stream().mapToLong(Long::valueOf).sum();
            birdData.add(totalCount);
        }


        // 첫 방문 시 인트로가 나오고, 그 이후는 안나오게 함
        // 기간은 1일
        // 최초 페이지 로드 여부를 확인하는 변수
//        boolean isFirstPage = false;
//        // 최초 페이지 로드인지 확인
//        if (request.getParameter("page") == null) {
//            isFirstPage = true;
//        }


        // 게시판 리스트 출력
        List<BoardDTO> boardDTOList = boardService.findAll();

        // 멸종 위기종 기준
        BirdGroup group = BirdGroup.I;

        // intro 여부
//        model.addAttribute("isFirstPage", isFirstPage);

        // 새의 통계 처리
        model.addAttribute("birdPage", birdPage); // 페이징 처리
        model.addAttribute("birdList", birdList); // 새의 총 개체수
        model.addAttribute("birdStatisticsDTOList", birdStatisticsDTOList); // 새 지역별 개체수
        model.addAttribute("birdLabels", birdLabels); // 새의 이름
        model.addAttribute("birdData", birdData); // 새의 지역별 개체수

        // 게시판 처리
        model.addAttribute("boardList", boardDTOList); // 게시판

        // 최신 뉴스 페이징 처리
        Page<NewsDTO> latestNews = newsService.findLatestNews(page, 4); // 최신 뉴스 4개까지 가져옴
        model.addAttribute("newNews", latestNews);

        // 과학뉴스
        Page<ScienceDTO> scienceNews = scienceonService.findScienceNews(scienceonPage, 4);// 과학 뉴스 4개까지 가져옴
        model.addAttribute("scienceNews", scienceNews);


        // 새의 앨범
        Page<BirdListFormDTO> birdblog = birdListService.getBirdListByBirdGroup(group, page, 3);
        model.addAttribute("birdblog", birdblog);
        
        
        // 쿠키를 확인하여 첫 방문 여부를 확인
        boolean isFirstVisit = true;
        Cookie[] cookies = request.getCookies(); // 요청에서 쿠키를 가져옴
        if (cookies != null) { // 쿠키가 존재할 경우
            for (Cookie cookie : cookies) {
                // 모든 쿠키를 순회 하면서 쿠키의 이름이 "visited"인 경우
                if ("birdys".equals(cookie.getName())) {
                    isFirstVisit = false; // false를 설정
                    break;
                }
            }
        }

        if (isFirstVisit) {
            // 첫 방문일 경우 인트로 페이지로 리다이렉트
            System.out.println("쿠기 결과 : " + isFirstVisit);

            // 쿠키 birdys를 생성함
            Cookie visitCookie = new Cookie("birdys", "true");
            // 60초, 60분, 24시간 , 1일
            visitCookie.setMaxAge(60 * 60 * 24 * 1); // 쿠키 유효기간 1일
            // 응답한 쿠키를 추가
            response.addCookie(visitCookie);
            
            // intro 기능을 실행
            boolean isFirstPage = true;
            model.addAttribute("isFirstPage", isFirstPage);
            return "index"; // 메인 페이지 이동
        } else {
            // 이미 방문한 경우
            System.out.println("쿠기 false 결과 : " + isFirstVisit);
            // intro 기능을 중지
            boolean isFirstPage = false;
            model.addAttribute("isFirstPage", isFirstPage);
            return "index"; // 메인 페이지 이동
        }
    }
}

