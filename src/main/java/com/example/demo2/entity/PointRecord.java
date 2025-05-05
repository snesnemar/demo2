package com.example.demo2.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 點數紀錄
 */
@Entity
@Data
@Table(name = "point_record")
public class PointRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 關聯 Member 表
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // 關聯 Activity 表
    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;

    // 使用 PointType enum 並儲存為字串
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PointType type;

    @Column(nullable = false)
    private int points;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

}
