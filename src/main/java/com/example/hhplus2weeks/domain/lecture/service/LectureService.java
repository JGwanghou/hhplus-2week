package com.example.hhplus2weeks.domain.lecture.service;

import com.example.hhplus2weeks.domain.lecture.Lecture;
import com.example.hhplus2weeks.domain.lecture.LectureHistory;
import com.example.hhplus2weeks.domain.lecture.LectureSchedule;
import com.example.hhplus2weeks.domain.lecture.exception.DuplicateRequestsException;
import com.example.hhplus2weeks.domain.lecture.repository.LectureHistoryRepository;
import com.example.hhplus2weeks.domain.lecture.repository.LectureRepository;
import com.example.hhplus2weeks.domain.lecture.repository.LectureScheduleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final LectureScheduleRepository lectureScheduleRepository;
    private final LectureHistoryRepository lectureHistoryRepository;
    private final LectureApplyValid lectureApplyValid;
    private ReentrantLock lock = new ReentrantLock();

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
    @Transactional(rollbackFor = {Exception.class})
    public LectureSchedule lectureScheduleApply(Long lectureScheduleId, Long userId) {
        LectureSchedule lectureSchedule = lectureScheduleRepository.lockFindByLectureScheduleId(lectureScheduleId);
        LectureSchedule apply = lectureSchedule.apply(lectureApplyValid, userId);
        lectureHistoryRepository.save(LectureHistory.create(null, lectureScheduleRepository.save(apply), userId));

        return lectureSchedule;
    }

    public Boolean lectureApplyCheck(Long userId, Long lectureScheduleId) {
        LectureSchedule lectureSchedule = lectureScheduleRepository.findById(lectureScheduleId);
        return lectureHistoryRepository.findLectureHistoryByLectureScheduleAndUserId(lectureSchedule, userId).isEmpty();
    }
}
