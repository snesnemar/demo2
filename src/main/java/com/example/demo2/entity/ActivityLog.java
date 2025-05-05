package com.example.demo2.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 活動參與
 */
@Entity
@Data
@Table(name = "activity_log")
public class ActivityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "activity_id")
    private Long activityId;

    @Column(name = "joined_at")
    private LocalDateTime joinedAt = LocalDateTime.now();

}
