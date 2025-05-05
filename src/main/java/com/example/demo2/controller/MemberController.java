package com.example.demo2.controller;

import com.example.demo2.entity.Member;
import com.example.demo2.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    /**
     * 取得會員基本資料
     */
//    @GetMapping("/{memberId}")
//    public Object getMemberInfo(@PathVariable Long memberId) {
//        Member member = memberService.getMemberInfo(memberId);
//        if (member != null) {
//            return member;
//        } else {
//            return "會員不存在";
//        }
//    }
    @GetMapping("/me")
    public Member getMyInfo(HttpSession session) {
        return (Member) session.getAttribute("member");
    }


    /**
     * 修改密碼
     */
    @PostMapping("/reset-password")
    public String resetPassword(HttpSession session,
                                @RequestParam String oldPassword,
                                @RequestParam String newPassword,
                                @RequestParam String confirmPassword) {
        Member member = (Member) session.getAttribute("member");
        if (member == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "尚未登入");
        }

        return memberService.resetPassword(member.getId(), oldPassword, newPassword, confirmPassword);
    }


    /**
     * 查詢目前會員總點數
     */
    @GetMapping("/{memberId}/points")
    public Integer getPoints(@PathVariable Long memberId) {
        return memberService.getPoints(memberId);
    }
}
