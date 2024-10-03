package com.example.hhplus2weeks.domain.lecture.repository;

import com.example.hhplus2weeks.domain.lecture.LectureHistory;
import com.example.hhplus2weeks.infrastructure.entity.LectureHistoryEntity;

public interface LectureHistoryRepository {
    LectureHistory save(LectureHistory lectureHistory);
}
