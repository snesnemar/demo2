package com.example.demo2.controller;

import com.example.demo2.entity.Member;
import com.example.demo2.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo2.model.ApiResponse;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    /**
     * 註冊會員
     */
    @PostMapping("/register")
    public ApiResponse register(@RequestParam String account,
                                                          @RequestParam String email,
                                                          @RequestParam String password,
                                                          @RequestParam String confirmPassword) {
        return authService.register(account, email, password, confirmPassword);
    }

    /**
     * 登入驗證
     */
    @PostMapping("/login")
    public ApiResponse login(@RequestParam String account,
                             @RequestParam String password,
                             HttpSession session) {
        try {
            Member member = authService.login(account, password);
            session.setAttribute("member", member); // 儲存會員資訊到 session
            return new ApiResponse(true, "登入成功", member);
        } catch (LoginException e) {
            return new ApiResponse(false, e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ApiResponse logout(HttpSession session) {
        session.invalidate(); // 清除整個 session
        return new ApiResponse(true, "登出成功");
    }

}
