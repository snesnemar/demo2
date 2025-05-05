package com.example.demo2.repository;

import com.example.demo2.entity.PointRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRecordRepository extends JpaRepository<PointRecord, Long> {

    // 查詢某會員的所有點數紀錄
    List<PointRecord> findByMemberId(Long memberId);

    // 聚合查詢：計算某會員總點數（加總 PointRecord 欄位）
    @Query("SELECT COALESCE(SUM(p.points), 0) FROM PointRecord p WHERE p.member.id = :memberId")
    Integer getTotalPointsByMemberId(@Param("memberId") Long memberId);

}
