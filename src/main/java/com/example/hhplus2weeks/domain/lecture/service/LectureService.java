package com.example.hhplus2weeks.domain.lecture.service;

import com.example.hhplus2weeks.api.dto.LectureHistoryResponse;
import com.example.hhplus2weeks.domain.lecture.Lecture;
import com.example.hhplus2weeks.domain.lecture.LectureHistory;
import com.example.hhplus2weeks.domain.lecture.LectureSchedule;
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

    // 신청완료한 특강 목록
    public List<LectureHistoryResponse> findLectureHistoryByUserId(Long userId) {
        // Step 1: 유저의 신청 기록을 가져옴
        List<LectureHistory> historyies = lectureHistoryRepository.findLectureHistoryByUserId(userId);

        // Step 2: 신청 기록에 해당하는 LectureSchedule 정보 조회
        List<LectureHistoryResponse> responseList = new ArrayList<>();

        for (LectureHistory history : historyies) {
            LectureSchedule schedule = lectureScheduleRepository.findById(history.getLectureSchedule().getId());

            // Step 3: 해당 강의 스케줄의 lecture_id로 LectureEntity를 조회
            Optional<Lecture> lecture = lectureRepository.getReferenceById(schedule.getLecture().getId());

            // LectureHistoryResponse에 필요한 데이터를 추가
            LectureHistoryResponse response = new LectureHistoryResponse(
                    lecture.get().getId(),   // Lecture ID
                    lecture.get().getTitle(), // Lecture Title
                    schedule.getSpeaker() // 강연자 정보
            );

            responseList.add(response);
        }

        return responseList;
    }
}
