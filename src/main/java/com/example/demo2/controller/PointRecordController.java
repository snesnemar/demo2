package com.example.demo2.controller;

import com.example.demo2.entity.PointRecord;
import com.example.demo2.repository.PointRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/points")
public class PointRecordController {

    @Autowired
    private PointRecordRepository pointRecordRepository;

    /**
     * 取得會員點數紀錄
     */
    @GetMapping
    public List<PointRecord> getPointRecords(@RequestParam Long memberId) {
        return pointRecordRepository.findByMemberId(memberId);
    }
}
