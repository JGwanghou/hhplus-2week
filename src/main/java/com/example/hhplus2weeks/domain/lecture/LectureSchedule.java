package com.example.hhplus2weeks.domain.lecture;

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

    public LectureSchedule apply() {
        this.registerCount += 1;
        return this;
    }
}
