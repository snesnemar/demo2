package com.example.demo2.repository;

import com.example.demo2.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    // 查詢目前正在進行中的活動
    List<Activity> findByStartTimeBeforeAndEndTimeAfter(LocalDateTime now1, LocalDateTime now2);

    // 查詢尚未開始的活動
    List<Activity> findByStartTimeAfter(LocalDateTime now);

    // 查詢已經結束的活動
    List<Activity> findByEndTimeBefore(LocalDateTime now);
}
