package com.example.hhplus2weeks.infrastructure;

import com.example.hhplus2weeks.domain.lecture.LectureSchedule;
import com.example.hhplus2weeks.infrastructure.entity.LectureScheduleEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LectureScheduleJpaRepository extends JpaRepository<LectureScheduleEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<LectureScheduleEntity> findLectureScheduleById(Long lectureScheduleId);

    List<LectureScheduleEntity> findByLectureDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
