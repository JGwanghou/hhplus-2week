package com.example.hhplus2weeks.infrastructure;

import com.example.hhplus2weeks.domain.lecture.LectureHistory;
import com.example.hhplus2weeks.domain.lecture.LectureSchedule;
import com.example.hhplus2weeks.infrastructure.entity.LectureHistoryEntity;
import com.example.hhplus2weeks.infrastructure.entity.LectureScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureHistoryJpaRepository extends JpaRepository<LectureHistoryEntity, Long> {

    Optional<LectureHistoryEntity> findLectureHistoryByLectureScheduleAndUserId(LectureScheduleEntity lectureScheduleEntity, Long userId);
}
