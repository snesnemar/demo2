package com.example.demo2.service;

import com.example.demo2.entity.Member;
import com.example.demo2.model.ApiResponse;
import com.example.demo2.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class AuthService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApiResponse register(String account, String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return new ApiResponse(false, "密碼與確認密碼不一致");
        }

        if (memberRepository.existsByAccount(account)) {
            return new ApiResponse(false, "帳號已存在");
        }

        if (memberRepository.existsByEmail(email)) {
            return new ApiResponse(false, "Email 已被註冊");
        }

        Member member = new Member();
        member.setAccount(account);
        member.setEmail(email);
        member.setPassword(passwordEncoder.encode(password));
        memberRepository.save(member);

        return new ApiResponse(true, "註冊成功");
    }

    public Member login(String account, String password) throws LoginException {
        Member member = memberRepository.findByAccount(account)
                .orElseThrow(() -> new LoginException("登入失敗，請檢查帳號密碼是否皆正確"));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new LoginException("登入失敗，請檢查帳號密碼是否皆正確");
        }

        return member;
    }


}
