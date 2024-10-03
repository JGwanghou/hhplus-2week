package com.example.hhplus2weeks.infrastructure;

import com.example.hhplus2weeks.domain.lecture.LectureSchedule;
import com.example.hhplus2weeks.infrastructure.entity.LectureScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureScheduleJpaRepository extends JpaRepository<LectureScheduleEntity, Long> {
    List<LectureScheduleEntity> findByLectureDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
