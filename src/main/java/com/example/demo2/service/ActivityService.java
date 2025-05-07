package com.example.demo2.service;

import com.example.demo2.entity.Activity;
import com.example.demo2.entity.ActivityLog;
import com.example.demo2.entity.PointRecord;
import com.example.demo2.entity.PointType;
import com.example.demo2.repository.ActivityRepository;
import com.example.demo2.repository.ActivityLogRepository;
import com.example.demo2.repository.PointRecordRepository;
import com.example.demo2.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo2.entity.Member;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Autowired
    private PointRecordRepository pointRecordRepository;

    @Autowired
    private MemberRepository memberRepository;

    /**
     * 查詢所有活動
     */
    public List<Activity> getAllActivities() {
        LocalDateTime now = LocalDateTime.now();
        List<Activity> list = activityRepository.findAll();

        list.forEach(activity -> {
            boolean expired = activity.getEndTime().isBefore(now);
            boolean notStarted = activity.getStartTime().isAfter(now);
            activity.setExpired(expired);
            activity.setNotStarted(notStarted);
        });

        return list;
    }


    /**
     * 讓會員參加活動
     */
    @Transactional
    public String joinActivity(Long memberId, Long activityId) {
        // 查活動是否存在
        Optional<Activity> optionalActivity = activityRepository.findById(activityId);
        if (optionalActivity.isEmpty()) {
            return "活動不存在";
        }

        Activity activity = optionalActivity.get();

        // 檢查是否已參加過
        Optional<ActivityLog> existing = activityLogRepository.findByMemberIdAndActivityId(memberId, activityId);
        if (existing.isPresent()) {
            return "你已經參加過此活動";
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("會員不存在"));

        // 新增活動紀錄
        ActivityLog log = new ActivityLog();
        log.setMemberId(memberId);
        log.setActivityId(activityId);
        log.setJoinedAt(LocalDateTime.now());
        activityLogRepository.save(log);

        // 新增點數紀錄
        PointRecord record = new PointRecord();
        record.setMember(member);
        record.setActivity(activity);
        record.setType(PointType.ADD);
        record.setPoints(activity.getRewardPoint());
        record.setCreatedAt(LocalDateTime.now());
        pointRecordRepository.save(record);

        return "參加成功，獲得 " + activity.getRewardPoint() + " 點";
    }


}
