package com.example.hhplus2weeks.domain.lecture;

import lombok.Getter;

@Getter
public class LectureHistory {
    private Long id;
    private LectureSchedule lectureSchedule;
    private Long userId;

    public LectureHistory(Long id, LectureSchedule lectureSchedule, Long userId) {
        this.id = id;
        this.lectureSchedule = lectureSchedule;
        this.userId = userId;
    }

    public static LectureHistory create(Long id, LectureSchedule lectureSchedule, Long userId) {
        return new LectureHistory(id, lectureSchedule, userId);
    }
}
