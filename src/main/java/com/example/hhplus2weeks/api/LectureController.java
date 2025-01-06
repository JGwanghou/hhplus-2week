package com.example.hhplus2weeks.api;

import com.example.hhplus2weeks.api.dto.ApplyRequest;
import com.example.hhplus2weeks.api.dto.LectureHistoryResponse;
import com.example.hhplus2weeks.domain.lecture.Lecture;
import com.example.hhplus2weeks.domain.lecture.LectureHistory;
import com.example.hhplus2weeks.domain.lecture.LectureSchedule;
import com.example.hhplus2weeks.domain.lecture.service.LectureService;
import com.example.hhplus2weeks.infrastructure.LectureJpaRepository;
import com.example.hhplus2weeks.infrastructure.entity.LectureEntity;
import com.example.hhplus2weeks.infrastructure.entity.LectureScheduleEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture")
public class LectureController {

    private final LectureService lectureService;

    /**
     *  특강 목록
     */
    @GetMapping
    public ResponseEntity<List<Lecture>> findAllLectureList(){
        return ResponseEntity.ok(lectureService.findAllLectureList());
    }

    /**
     *  특강 스케줄 목록(날짜별로 신청 가능한 특강목록 나타내기)
     */
    @GetMapping("/{date}/schedules")
    public ResponseEntity<List<LectureSchedule>> findByLectureDateTimeBetween(@PathVariable String date){
        return ResponseEntity.ok(lectureService.findByLectureDateTimeBetween(date));
    }

    /**
     *  특강 신청
     */
    @PostMapping("{lectureScheduleId}/apply")
    public ResponseEntity<LectureSchedule> apply(@PathVariable Long lectureScheduleId, @RequestBody ApplyRequest applyRequest){
        return ResponseEntity.ok(lectureService.lectureScheduleApply(lectureScheduleId, applyRequest.getUserId()));
    }

    /**
     *  특강 신청 완료 목록 조회
     */
    @GetMapping("/{userId}/histories")
    public ResponseEntity<List<LectureHistoryResponse>> history(@PathVariable Long userId){
        return ResponseEntity.ok(lectureService.findLectureHistoryByUserId(userId));
    }
}
