package com.keduit.bird.service;

import com.keduit.bird.constant.BirdGroup;
import com.keduit.bird.dto.BirdListFormDTO;
import com.keduit.bird.entity.BirdList;
import com.keduit.bird.repository.BirdRepository;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BirdListService {

    @Autowired
    private BirdRepository birdRepository;

    public List<BirdList> getBirdListByBirdGroup2(BirdGroup group) {
        return birdRepository.findByBirdGroup(group);
    }

    public Page<BirdListFormDTO> getBirdListByBirdGroup(BirdGroup group, int page, int size) {


        return birdRepository.findByBirdGroup(group,PageRequest.of(page,size)).map(this::birdListFormDTO);
    }

    public BirdListFormDTO birdListFormDTO(BirdList birdList){

        BirdListFormDTO birdListFormDTO = new BirdListFormDTO();

        birdListFormDTO.setId(birdList.getId());
        birdListFormDTO.setBirdName(birdList.getBirdName());
        birdListFormDTO.setBirdDetail(birdList.getBirdDetail());
        birdListFormDTO.setBirdImgUrl(birdList.getBirdImgUrl());
        birdListFormDTO.setGroup(birdList.getBirdGroup());
        return birdListFormDTO;
    }


    //크롤링으로 데이터 긁어오기
    private static final int PTAGE_LIMIT = 3;

    @Autowired
    public void BirdListService(BirdRepository birdRepository) {
        this.birdRepository = birdRepository;
    }

    @PostConstruct
    @Transactional
    public void SaveBird() {

// ----- 기본 리스트 (ㄱ ~ ㅎ) -----
//        try {
//            // 페이지 URL, 절대경로 만들기
////            String url = "https://ko.wikipedia.org/w/index.php?title=%EB%B6%84%EB%A5%98:%ED%95%9C%EA%B5%AD%EC%9D%98_%EC%83%88";
//            //1페이지
//            String url = "https://ko.wikipedia.org/w/index.php?title=%EB%B6%84%EB%A5%98:%ED%95%9C%EA%B5%AD%EC%9D%98_%EC%83%88&pagefrom=%EC%A0%81%EA%B0%88%EC%83%89%ED%9D%B0%EC%A3%BD%EC%A7%80#mw-pages";
//            //2페이지
//            List<String> links = new ArrayList<>();
//
//            Document doc = Jsoup.connect(url).get();
//            Element outerDiv = doc.selectFirst("div.mw-category.mw-category-columns"); // 바깥쪽 div 선택
//            Elements secondInnerDiv = outerDiv.select("div.mw-category-group").eq(5); // 두 번째 안쪽 div 선택
////            첫페이지에 ㄱ~ㅈ(ㄹ없음)까지 (1~8)8개의 ul이 있고, 2페이지에 ㅈ~ㅎ까지 (0~5)6개의 ul이 있음.
//
//            for (Element category : secondInnerDiv) {
//                Elements categoryLinks = category.select("[href]");
//                for (Element link : categoryLinks) {
//                    String listUrl = link.attr("href");
//                    Document linkDoc = Jsoup.connect("https://ko.wikipedia.org/" + listUrl).get();
//
//                    Element imgElement = linkDoc.selectFirst(".mw-file-description img[src]");
//                    String imgUrl = imgElement.attr("src");
//                    // 이미지 URL 가져오기
//
//                    Element headingElement = linkDoc.selectFirst("h1#firstHeading span.mw-page-title-main");
//                    String name = headingElement.text();
//
//                    Element contentElement = linkDoc.selectFirst(".mw-content-ltr.mw-parser-output");
//                    Elements pTags = contentElement.select("p");
//
//                    StringBuilder detail = new StringBuilder();
//                    boolean isFirstPTag = true;
//                    int paragraphCount = 0;
//
//                    for (Element pTag : pTags) {
//                        if (paragraphCount >= PTAGE_LIMIT) {
//                            break;
//                        }
//                        if (!isFirstPTag) { // 첫 번째 p 태그가 아니라면
//                            detail.append(pTag.text()).append("\n"); // 내용을 StringBuilder에 추가
//                            paragraphCount++;
//                        } else {
//                            isFirstPTag = false; // 첫 번째 p 태그였음을 표시
//                        }
//                    }
//
//                    // BirdList 객체 생성 및 값 설정
//                    BirdList birdList = new BirdList();
//                    birdList.setBirdName(name);
//                    birdList.setBirdDetail(detail.toString());
//                    birdList.setBirdImgUrl(imgUrl);
//                    birdList.setBirdGroup(BirdGroup.HA);
//                    // 1p: ga, na, da, ma, ba, sa, aa, ja
//                    // 2p: ja, cha, ka, ta, pa, ha
//
//                    birdRepository.save(birdList);
//
//                    System.out.println(name);
//                    System.out.println(detail);
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

// ----- 기본리스트 끝 -----

// ----- 멸종위기종 -----
//
//        try {
//            // 웹 페이지에서 HTML 가져오기
//            Document doc = Jsoup.connect("https://ko.wikipedia.org/wiki/%ED%99%98%EA%B2%BD%EB%B6%80_%EC%A7%80%EC%A0%95_%EB%A9%B8%EC%A2%85%EC%9C%84%EA%B8%B0_%EC%95%BC%EC%83%9D%EC%83%9D%EB%AC%BC").get();
//
//            // 1급 멸종위기종 테이블
//            Element table = doc.select("table.wikitable").get(3);
//            //세번째, 네번째 테이블 가져오기
//            Element tbody = table.selectFirst("tbody");
//            Elements trs = tbody.select("tr");
//
//            for(Element tr : trs) {
//                Elements aTag = tr.select("td a");
//                if (aTag == null) {
//                    continue; // 이미지가 없으면 다음 항목으로 넘어감
//                }
//                    String birdName = aTag.text();
//                    String detailUrl = "https://ko.wikipedia.org" + aTag.attr("href");
//
//                    Document linkDoc = Jsoup.connect(detailUrl).get();
//
//                    Element imgElement = linkDoc.selectFirst(".mw-file-description img[src]");
//                    String imgUrl = imgElement != null ? imgElement.attr("src") : null;
//                    // 이미지 URL 가져오기
//                    if (imgUrl == null) {
//                        continue; // 이미지가 없으면 다음 항목으로 넘어감
//                    }
//
//                    Element headingElement = linkDoc.selectFirst("h1#firstHeading span.mw-page-title-main");
//                    String name = headingElement.text();
//
//                    Element contentElement = linkDoc.selectFirst(".mw-content-ltr.mw-parser-output");
//                    Elements pTags = contentElement.select("p");
//
//                    StringBuilder detail = new StringBuilder();
//                    boolean isFirstPTag = true;
//                    int paragraphCount = 0;
//
//                    for (Element pTag : pTags) {
//                        if (paragraphCount >= PTAGE_LIMIT) {
//                            break;
//                        }
//                        if (!isFirstPTag) { // 첫 번째 p 태그가 아니라면
//                            detail.append(pTag.text()).append("\n"); // 내용을 StringBuilder에 추가
//                            paragraphCount++;
//                        } else {
//                            isFirstPTag = false; // 첫 번째 p 태그였음을 표시
//                        }
//                    }
//
//                    // BirdList 객체 생성 및 값 설정
//                    BirdList birdList = new BirdList();
//                    birdList.setBirdName(name);
//                    birdList.setBirdDetail(detail.toString());
//                    birdList.setBirdImgUrl(imgUrl);
//                    birdList.setBirdGroup(BirdGroup.II);
//                    // 1급 : I, 2급 : II
//
//                    birdRepository.save(birdList);
//
//                    System.out.println(name);
//                    System.out.println(detail);
//                }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}