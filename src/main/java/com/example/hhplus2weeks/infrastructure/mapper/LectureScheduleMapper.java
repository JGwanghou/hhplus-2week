package com.example.hhplus2weeks.infrastructure.mapper;

import com.example.hhplus2weeks.domain.lecture.Lecture;
import com.example.hhplus2weeks.domain.lecture.LectureSchedule;
import com.example.hhplus2weeks.infrastructure.entity.LectureEntity;
import com.example.hhplus2weeks.infrastructure.entity.LectureScheduleEntity;

public class LectureScheduleMapper {

    // Entity -> Domain 변환
    public static LectureSchedule toDomain(LectureScheduleEntity lectureScheduleEntity) {
        return LectureSchedule.create(
                lectureScheduleEntity.getId(),
                LectureMapper.toDomain(lectureScheduleEntity.getLecture()),
                lectureScheduleEntity.getSpeaker(),
                lectureScheduleEntity.getRegisterCount(),
                lectureScheduleEntity.getRegisterMaxCount(),
                lectureScheduleEntity.getLectureDateTime()
        );
    }

    public static LectureScheduleEntity toEntity(LectureSchedule lectureSchedule) {
        return new LectureScheduleEntity(lectureSchedule.getId(),
                LectureMapper.toEntity(lectureSchedule.getLecture()),
                lectureSchedule.getSpeaker(),
                lectureSchedule.getRegisterCount(),
                lectureSchedule.getRegisterMaxCount(),
                lectureSchedule.getLectureDateTime());
    }
}
