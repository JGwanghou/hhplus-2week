package com.example.hhplus2weeks.domain.lecture;

import com.example.hhplus2weeks.domain.lecture.exception.DuplicateRequestsException;
import com.example.hhplus2weeks.domain.lecture.service.LectureApplyValid;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LectureSchedule {
    private Long id;
    private Lecture lecture;
    private String speaker;
    private int registerCount;
    private int registerMaxCount;
    private LocalDateTime lectureDateTime;

    public LectureSchedule(Long id, Lecture lecture, String speaker, int registerCount, int registerMaxCount, LocalDateTime lectureDateTime) {
        this.id = id;
        this.lecture = lecture;
        this.speaker = speaker;
        this.registerCount = registerCount;
        this.registerMaxCount = registerMaxCount;
        this.lectureDateTime = lectureDateTime;
    }

    public static LectureSchedule create(Long id, Lecture lecture, String speaker, int registerCount, int registerMaxCount, LocalDateTime lectureDateTime){
        return new LectureSchedule(id, lecture, speaker, registerCount, registerMaxCount, lectureDateTime);
    }

    public LectureSchedule apply(LectureApplyValid lectureApplyValid, Long userId) {
        if (lectureApplyValid.isApplyCheck(this, userId)) {
            throw new DuplicateRequestsException("이미 신청된 강의입니다.");
        }

        this.registerCount += 1;
        return this;
    }
}
