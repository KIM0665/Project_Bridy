package com.keduit.bird.controller;

import com.keduit.bird.constant.Role;
import com.keduit.bird.dto.MemberFormDTO;
import com.keduit.bird.entity.Member;
import com.keduit.bird.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;



    @GetMapping("/admins")
    public String birdAdmin(Model model){
        List<Member> members = memberService.getAllMembers();

        model.addAttribute("members", members);

        return "admin/birdManiger";
    }

    @GetMapping("/members")
    public String listMembers(Model model) {
        List<Member> members = memberService.getAllMembers();

        model.addAttribute("members", members);
        return "admin/memberList";
    }

    @PostMapping("/members/{id}/edit")
    public String editMember(@PathVariable Long id, @ModelAttribute MemberFormDTO memberFormDTO) {
        memberService.updateMember(id, memberFormDTO);
        return "redirect:/admin/memberList/{id}";
    }

    @PostMapping("/members/{id}/delete")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "redirect:/admin/memberList";
    }

    @GetMapping("/search")
    public String searchMembers(@RequestParam("term") String term, Model model) {
        List<Member> members = memberService.searchMembers(term);
        model.addAttribute("members", members);
        return "admin/memberList"; // 회원 목록 페이지로 이동
    }

    @GetMapping("/roleUpdate/{memberId}")
    public String roleUpdateForm(@PathVariable Long memberId, Model model) {
        Member member = memberService.getMemberById(memberId);
        model.addAttribute("member", member);

        return "admin/manigerUpdate"; // 회원 목록 페이지로 이동
    }
    @PostMapping("/roleUpdate")
    public String roleUpdateMembers(@RequestParam("member") Member member) {

        return "admin/memberList"; // 회원 목록 페이지로 이동
    }
}
