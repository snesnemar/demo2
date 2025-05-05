package com.example.demo2.controller;

import com.example.demo2.entity.Activity;
import com.example.demo2.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 查詢所有活動
     */
    @GetMapping
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    /**
     * 參加活動（會員點擊按鈕時）
     */
    @PostMapping("/join")
    public String joinActivity(@RequestParam Long memberId,
                               @RequestParam Long activityId) {
        return activityService.joinActivity(memberId, activityId);
    }
}
