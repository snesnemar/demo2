package com.example.demo2.service;

import com.example.demo2.entity.Member;
import com.example.demo2.repository.MemberRepository;
import com.example.demo2.repository.PointRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PointRecordRepository pointRecordRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根據會員 ID 取得會員基本資料
     */
    public Member getMemberInfo(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    /**
     * 修改密碼
     */
    public String resetPassword(Long memberId, String oldPassword, String newPassword, String confirmPassword) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        if (optionalMember.isEmpty()) {
            return "會員不存在";
        }

        Member member = optionalMember.get();

        // 比對舊密碼
        if (!passwordEncoder.matches(oldPassword, member.getPassword())) {
            return "舊密碼錯誤";
        }
        // 比對新密碼與確認密碼
        if (!newPassword.equals(confirmPassword)) {
            return "新密碼與確認密碼不一致";
        }

        // 設定新密碼
        member.setPassword(passwordEncoder.encode(newPassword));
        memberRepository.save(member);

        return "密碼修改成功";
    }

    /**
     * 計算目前會員點數（從 point_record 加總）
     */
    public Integer getPoints(Long memberId) {
        return pointRecordRepository.getTotalPointsByMemberId(memberId);
    }
}
