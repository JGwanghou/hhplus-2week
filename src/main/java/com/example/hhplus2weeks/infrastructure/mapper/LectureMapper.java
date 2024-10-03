package com.example.hhplus2weeks.infrastructure.mapper;

import com.example.hhplus2weeks.domain.lecture.Lecture;
import com.example.hhplus2weeks.infrastructure.entity.LectureEntity;

public class LectureMapper {

    // Entity -> Domain 변환
    public static Lecture toDomain(LectureEntity lectureEntity) {
        return Lecture.create(lectureEntity.getId(), lectureEntity.getTitle());
    }

    // Domain -> Entity 변환
    public static LectureEntity toEntity(Lecture lecture) {
        return new LectureEntity(lecture.getId(), lecture.getTitle());
    }
}
