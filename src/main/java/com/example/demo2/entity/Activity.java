package com.example.demo2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 活動設定
 */
@Entity
@Data
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "reward_point")
    private Integer rewardPoint;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @OneToMany(mappedBy = "activity")
    @JsonIgnore
    private List<PointRecord> pointRecords;


    @Transient  // 表示這不是資料表裡的欄位，不會寫進資料庫
    private boolean expired;

    public boolean isExpired() {
        return endTime != null && endTime.isBefore(LocalDateTime.now());
    }

    public boolean getExpired() {
        return isExpired();
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    @Transient
    private boolean notStarted;

    public boolean isNotStarted() {
        return startTime != null && startTime.isAfter(LocalDateTime.now());
    }
    public void setNotStarted(boolean notStarted) {
        this.notStarted = notStarted;
    }

}
