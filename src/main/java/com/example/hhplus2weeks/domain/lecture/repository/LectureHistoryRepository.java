package com.example.hhplus2weeks.domain.lecture.repository;

import com.example.hhplus2weeks.domain.lecture.LectureHistory;
import com.example.hhplus2weeks.domain.lecture.LectureSchedule;
import com.example.hhplus2weeks.infrastructure.entity.LectureScheduleEntity;

import java.util.Optional;

public interface LectureHistoryRepository {
    LectureHistory save(LectureHistory lectureHistory);
    Optional<LectureHistory> findLectureHistoryByLectureScheduleAndUserId(LectureSchedule lectureSchedule, Long userId);
}
