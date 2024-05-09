package com.keduit.bird.service;

import com.keduit.bird.dto.MemberFormDTO;
import com.keduit.bird.entity.Member;
import com.keduit.bird.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.keduit.bird.constant.Role.STOP;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //마이페이지에 사용자 정보 가져오기(읽기 전용)
    @Transactional(readOnly = true)
    public MemberFormDTO getMemberDtl(String memberEmail) {
        Member member = memberRepository.findByMemberEmail(memberEmail);
        MemberFormDTO memberFormDTO = MemberFormDTO.of(member);
        return memberFormDTO;
    }

    //회원 권한 박탈시키기(STOP: 부여된 권한 없음 = all 허용 가능 외 접근 불가.)
    public void memberStop(Member member){
        if(member != null){
            // id로 회원을 조회해 권한 변경(stop)
            member.setRole(STOP);
            memberRepository.save(member);
        }
    }
    //회원 정보 수정
    public void updateMemberInfo(String memberName, String memberPhone, String memberEmail) throws Exception{
        memberRepository.updateMemberInfo(memberName, memberPhone, memberEmail);
    }

    // 모든 회원 조회
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // ID로 회원 조회
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 회원이 존재하지 않습니다: " + id));
    }

    // 회원 정보 업데이트
    public void updateMember(Long id, MemberFormDTO memberFormDTO) {
        Member member = getMemberById(id);
        // MemberFormDTO를 사용하여 업데이트 로직 구현
        // 예: member.setEmail(memberFormDTO.getEmail());
        //    member.setName(memberFormDTO.getName());
        memberRepository.save(member);
    }

    // 회원 삭제
    public void deleteMember(Long id) {
        Member member = getMemberById(id);
        memberRepository.delete(member);
    }

    // 회원 권한
//    public void updateRoleMember(String memberEmail, String memberRole){
//
//        Member member = memberRepository.findByMemberEmail(memberEmail);
//        memberRepository.updateRoleMember(memberEmail, memberRole);
//        memberRepository.save(member);
//    }

    public List<Member> searchMembers(String term) {
        // 회원 이름 또는 이메일에 검색어가 포함된 회원을 찾아서 반환
        return memberRepository.findByMemberNameContainingIgnoreCaseOrMemberEmailContainingIgnoreCase(term, term);
    }

}
