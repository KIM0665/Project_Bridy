package com.keduit.bird.controller;

import com.keduit.bird.constant.BirdGroup;
import com.keduit.bird.entity.BirdList;
import com.keduit.bird.entity.Birds;
import com.keduit.bird.service.BirdListService;
import com.keduit.bird.service.BirdService;
import com.keduit.bird.service.BirdStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/bird")
@RequiredArgsConstructor
public class BirdListController {

    @Autowired
    private BirdListService birdListService;
    private final BirdService birdService;
    private final BirdStatisticsService birdStatisticsService;

    @GetMapping("/birds")
    public String getBird(Model model, @RequestParam(required = false) String groupValue) {

        // 기본 group 값을 ga로 설정.
        BirdGroup group = BirdGroup.GA;

        // 하나의 페이지를 사용해 여러 리스트를 보여주기 위해서 switch문을 사용해 리스트를 구분할 group값 변경하기.
        // (= 버튼에 value = "group" 값 지정.)
        if(groupValue != null){
            switch(groupValue){
                case "NA" : group = BirdGroup.NA;
                    break;
                case "DA" : group = BirdGroup.DA;
                    break;
                case "MA" : group = BirdGroup.MA;
                    break;
                case "BA" : group = BirdGroup.BA;
                    break;
                case "SA" : group = BirdGroup.SA;
                    break;
                case "AA" : group = BirdGroup.AA;
                    break;
                case "JA" : group = BirdGroup.JA;
                    break;
                case "CHA" : group = BirdGroup.CHA;
                    break;
                case "KA" : group = BirdGroup.KA;
                    break;
                case "TA" : group = BirdGroup.TA;
                    break;
                case "PA" : group = BirdGroup.PA;
                    break;
                case "HA" : group = BirdGroup.HA;
                    break;
                    //멸종위기종
                case "I" : group = BirdGroup.I;
                    break;
                case "II" : group = BirdGroup.II;
                    break;
                    //따로 지정 값이 없다면(큰 카테고리명을 클릭했을 경우) 기본 ga 값으로 지정되어 'ㄱ'리스트 보여짐.
                default: group = BirdGroup.GA;
                    break;
            }
        }

        List<BirdList> birdList = birdListService.getBirdListByBirdGroup2(group);
        model.addAttribute("birdList", birdList);
        return "bird/birdListForm";
        //사용자가 선택한 group 값을 가지고 해당 페이지로 이동
    }

    @GetMapping("/birdy")
    // 새의 탐조
    public String showBirds(@RequestParam(required = false) String type, Model model) {
        List<Birds> birds = birdService.showBirds(type);
        model.addAttribute("birds", birds);
        return "bird/birdybird";
    }

    @PostMapping("/birdstic")
    public String birdChart(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size)  {

        Map<String, Object> responseData = new HashMap<>();

        Pageable pageable = PageRequest.of(page -1, size);
        Page<Map.Entry<String, Map<String, Long>>> birdPage = birdStatisticsService.getBirdPage(pageable);


        // 새 지역별 개체수 파악
        Map<String, Map<String, Long>> birdStatisticsDTOList = birdStatisticsService.getTotalCountByBirdName();

        // 새 총 개체수와 새 이름 목록 가져오기
        Map<String, Long> birdName = birdStatisticsService.getTotalCountBirdName(page, size);
        Map<String, Long> birdList = birdStatisticsService.getTotalBirdName();


        responseData.put("birdStatisticsDTOList", birdStatisticsDTOList);
        responseData.put("birdName", birdName);
        responseData.put("birdList", birdList);
        responseData.put("birdPage", birdPage);
        return "bird/shop";
    }



}
