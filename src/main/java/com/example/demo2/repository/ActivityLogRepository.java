package com.example.demo2.repository;

import com.example.demo2.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {

    // 查詢某會員是否已參加過某活動
    Optional<ActivityLog> findByMemberIdAndActivityId(Long memberId, Long activityId);

    // 查詢某會員參加過的所有活動紀錄
    List<ActivityLog> findByMemberId(Long memberId);
}
