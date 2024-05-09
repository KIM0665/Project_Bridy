package com.keduit.bird.dto;

import com.keduit.bird.entity.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class MemberFormDTO {

    private Long id;

    @NotBlank(message = "사용하실 닉네임을 입력해주세요.")
    private String memberName;

    @NotEmpty(message = "이메일 형식에 맞춰 작성해주세요.")
    @Email(message = "이메일 형식에 맞춰 작성해주세요.")
    private String memberEmail;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Length(min = 4, max = 20, message = "비밀번호는 8자 이상, 20자 미만으로 설정해주세요.")
    private String memberPwd;

    @NotEmpty(message = "연락처를 입력해주세요.")
    private String memberPhone;

    private ProfileDTO profileDTO;
    private Long profileId;

    //modelmapper 생성.
    private static ModelMapper modelMapper = new ModelMapper();

    //Member <-> MemberFormDTO 전환작업(그래야 맵핑한 데이터들을 객체로 저장시킬 수 있음)
    public Member createMember(){
        return modelMapper.map(this, Member.class);
    }
    public static MemberFormDTO of(Member member){
        return modelMapper.map(member, MemberFormDTO.class);
    }
}