package com.keduit.bird.dto;

import com.keduit.bird.constant.BirdGroup;
import com.keduit.bird.entity.BirdList;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class BirdListFormDTO {

    private Long id;

    @NotNull(message = "개체명은 필수 입력입니다.")
    private String birdName;

    @NotNull(message = "개체에 대한 설명문을 작성해주세요.")
    private String birdDetail;

    private String birdImgUrl;
    private BirdGroup group;

    private static ModelMapper modelMapper = new ModelMapper();

    public BirdList createBird(){
        return modelMapper.map(this, BirdList.class);
    }
    public static BirdListFormDTO of(BirdList birdList){
        return modelMapper.map(birdList, BirdListFormDTO.class);
    }
}
