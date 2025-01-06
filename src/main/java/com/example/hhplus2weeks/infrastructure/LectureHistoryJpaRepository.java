package com.example.hhplus2weeks.infrastructure;

import com.example.hhplus2weeks.api.dto.LectureHistoryResponse;
import com.example.hhplus2weeks.domain.lecture.LectureHistory;
import com.example.hhplus2weeks.domain.lecture.LectureSchedule;
import com.example.hhplus2weeks.infrastructure.entity.LectureHistoryEntity;
import com.example.hhplus2weeks.infrastructure.entity.LectureScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LectureHistoryJpaRepository extends JpaRepository<LectureHistoryEntity, Long> {

    Optional<LectureHistoryEntity> findLectureHistoryByLectureScheduleAndUserId(LectureScheduleEntity lectureScheduleEntity, Long userId);

    // 유저 번호로 신청 기록을 조회
    List<LectureHistoryEntity> findByUserId(Long userId);
}
