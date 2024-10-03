package com.example.hhplus2weeks.infrastructure;

import com.example.hhplus2weeks.domain.lecture.LectureSchedule;
import com.example.hhplus2weeks.domain.lecture.repository.LectureScheduleRepository;
import com.example.hhplus2weeks.infrastructure.entity.LectureScheduleEntity;
import com.example.hhplus2weeks.infrastructure.mapper.LectureScheduleMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LectureScheduleRepositoryImpl implements LectureScheduleRepository {
    private final LectureScheduleJpaRepository lectureScheduleJpaRepository;


    @Override
    public LectureSchedule lockFindByLectureScheduleId(Long lectureScheduleId) {
        return LectureScheduleMapper.toDomain(lectureScheduleJpaRepository.findLectureScheduleById(lectureScheduleId).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public LectureSchedule save(LectureSchedule lectureSchedule) {
        return LectureScheduleMapper.toDomain(lectureScheduleJpaRepository.save(LectureScheduleMapper.toEntity(lectureSchedule)));
    }

    @Override
    public LectureSchedule findById(Long lectureScheduleId) {
        return LectureScheduleMapper.toDomain(lectureScheduleJpaRepository.findById(lectureScheduleId)
                .orElseThrow(EntityNotFoundException::new));
    }

    @Override
    public List<LectureSchedule> findByLectureDateTimeBetween(LocalDateTime start, LocalDateTime end) {
        return lectureScheduleJpaRepository.findByLectureDateTimeBetween(start, end).stream()
                .map(LectureScheduleMapper::toDomain)
                .collect(Collectors.toList());
    }
}
