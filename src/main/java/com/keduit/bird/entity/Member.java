package com.keduit.bird.entity;



import com.keduit.bird.constant.Role;
import com.keduit.bird.dto.MemberFormDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="member_id")
    private Long id;

    private String memberName;

    @Column(unique=true)
    private String memberEmail;

    private String memberPwd;

    private String memberPhone;

    //소셜 로그인 여부
    private boolean social;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDTO memberFormDTO,
                                      PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setMemberName(memberFormDTO.getMemberName());
        member.setMemberEmail(memberFormDTO.getMemberEmail());
        String memberPwd = passwordEncoder.encode(memberFormDTO.getMemberPwd());
        member.setMemberPwd(memberPwd);
        member.setMemberPhone(memberFormDTO.getMemberPhone());
        member.setRole(Role.ADMIN);
        return member;
    }

//    관리자용으로 메서드를 새로 만들어도 되지만, 그럼 관리자용 코드를 또 만들어주어야하니...
//    편히 갑시다, 우리.

    //비밀번호 변경
    public static String updatePwd(String newPwd, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.memberPwd = passwordEncoder.encode(newPwd);
        return member.memberPwd;
    }
}