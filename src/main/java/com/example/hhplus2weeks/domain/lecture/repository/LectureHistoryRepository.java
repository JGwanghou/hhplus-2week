package com.example.hhplus2weeks.domain.lecture.repository;

import com.example.hhplus2weeks.api.dto.LectureHistoryResponse;
import com.example.hhplus2weeks.domain.lecture.LectureHistory;
import com.example.hhplus2weeks.domain.lecture.LectureSchedule;
import com.example.hhplus2weeks.infrastructure.entity.LectureScheduleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LectureHistoryRepository {
    LectureHistory save(LectureHistory lectureHistory);
    Optional<LectureHistory> findLectureHistoryByLectureScheduleAndUserId(LectureSchedule lectureSchedule, Long userId);
    List<LectureHistory> findLectureHistoryByUserId(Long userId);

}
