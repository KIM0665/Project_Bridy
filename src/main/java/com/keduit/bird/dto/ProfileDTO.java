package com.keduit.bird.dto;

import com.keduit.bird.entity.Profile;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@ToString
public class ProfileDTO {

    private Long id;
    private String profileOrigin;
    private String profileName;
    private String profileUrl;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ProfileDTO of(Profile profile){
        return modelMapper.map(profile, ProfileDTO.class);
    }

}
