package com.example.hhplus2weeks.infrastructure.mapper;

import com.example.hhplus2weeks.domain.lecture.LectureHistory;
import com.example.hhplus2weeks.infrastructure.entity.LectureHistoryEntity;

public class LectureHistoryMapper {
    public static LectureHistory toDomain(LectureHistoryEntity lectureHistoryEntity) {
        return LectureHistory.create(lectureHistoryEntity.getId(),
                LectureScheduleMapper.toDomain(lectureHistoryEntity.getLectureSchedule()),
                lectureHistoryEntity.getUserId());
    }

    public static LectureHistoryEntity toEntity(LectureHistory lectureHistory) {
        return new LectureHistoryEntity(lectureHistory.getId(),
                LectureScheduleMapper.toEntity(lectureHistory.getLectureSchedule()),
                lectureHistory.getUserId());
    }
}
