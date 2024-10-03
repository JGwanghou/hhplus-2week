package com.example.hhplus2weeks.domain.lecture.repository;

import com.example.hhplus2weeks.domain.lecture.LectureSchedule;
import com.example.hhplus2weeks.infrastructure.entity.LectureHistoryEntity;
import com.example.hhplus2weeks.infrastructure.entity.LectureScheduleEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LectureScheduleRepository {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    LectureSchedule lockFindByLectureScheduleId(Long lectureScheduleId);
    LectureSchedule save(LectureSchedule lectureSchedule);
    List<LectureSchedule> findByLectureDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
