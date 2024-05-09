package com.keduit.bird.repository;



import com.keduit.bird.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberEmail(String memberEmail);

    //이메일을 기반으로 회원을 찾아 비밀번호 변경하기
    @Modifying
    @Query("UPDATE Member m SET m.memberPwd = :memberPwd WHERE m.memberEmail = :memberEmail")
    public void updatePassword(@Param("memberPwd") String memberPwd,
                        @Param("memberEmail") String memberEmail);

    @Query("select m from Member m where m.memberEmail = :memberEmail and m.social=false")
    Optional<Member> getWithRoles(String memberEmail);
    // 소셜 로그인을 했을 때.

    //회원 정보 업데이트
    @Modifying
    @Query("UPDATE Member m SET m.memberName = :memberName, m.memberPhone = :memberPhone WHERE m.memberEmail = :memberEmail")
    public void updateMemberInfo(@Param("memberName") String memberName,
                          @Param("memberPhone") String memberPhone,
                          @Param("memberEmail") String memberEmail);

    List<Member> findByMemberNameContainingIgnoreCaseOrMemberEmailContainingIgnoreCase(String name, String email);

}
