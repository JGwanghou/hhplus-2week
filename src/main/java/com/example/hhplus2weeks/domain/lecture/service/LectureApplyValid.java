package com.example.hhplus2weeks.domain.lecture.service;

import com.example.hhplus2weeks.domain.lecture.LectureSchedule;
import com.example.hhplus2weeks.domain.lecture.repository.LectureHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LectureApplyValid {
    private final LectureHistoryRepository lectureHistoryRepository;

    public boolean isApplyCheck(LectureSchedule lectureSchedule, Long userId) {
        return lectureHistoryRepository.findLectureHistoryByLectureScheduleAndUserId(lectureSchedule, userId).isPresent();
    }
}
