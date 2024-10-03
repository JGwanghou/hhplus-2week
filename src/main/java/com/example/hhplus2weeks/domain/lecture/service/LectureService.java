package com.example.hhplus2weeks.domain.lecture.service;

import com.example.hhplus2weeks.domain.lecture.Lecture;
import com.example.hhplus2weeks.domain.lecture.LectureHistory;
import com.example.hhplus2weeks.domain.lecture.LectureSchedule;
import com.example.hhplus2weeks.domain.lecture.repository.LectureHistoryRepository;
import com.example.hhplus2weeks.domain.lecture.repository.LectureRepository;
import com.example.hhplus2weeks.domain.lecture.repository.LectureScheduleRepository;
import com.example.hhplus2weeks.infrastructure.entity.LectureEntity;
import com.example.hhplus2weeks.infrastructure.entity.LectureScheduleEntity;
import com.example.hhplus2weeks.infrastructure.mapper.LectureHistoryMapper;
import com.example.hhplus2weeks.infrastructure.mapper.LectureMapper;
import com.example.hhplus2weeks.infrastructure.mapper.LectureScheduleMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final LectureScheduleRepository lectureScheduleRepository;
    private final LectureHistoryRepository lectureHistoryRepository;

    public List<Lecture> findAllLectureList() {
        return lectureRepository.findAllLectureList();
    }

    // 특강 스케줄 목록
    public List<LectureSchedule> findByLectureDateTimeBetween(String date) {
        LocalDate localDate = LocalDate.parse(date);
        LocalDateTime start = localDate.atStartOfDay(); // 특정 날짜의 시작 시간: 00:00:00
        LocalDateTime end = localDate.atTime(23, 59, 59); // 특정 날짜의 끝 시간: 23:59:59

        return lectureScheduleRepository.findByLectureDateTimeBetween(start, end);

    }


    // 특강 신청
    public LectureSchedule lectureScheduleApply(Long lectureScheduleId, Long userId) {
        // 특강 스케쥴 테이블 lectureScheduleId로 조회
        LectureSchedule lectureSchedule = lectureScheduleRepository.lockFindByLectureScheduleId(lectureScheduleId);

        // 해당 id registerCnt 1증가
        LectureSchedule apply = lectureSchedule.apply();
        lectureHistoryRepository.save(LectureHistory.create(null, lectureScheduleRepository.save(apply), userId));

        return lectureSchedule;
    }
}
