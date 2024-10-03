package com.example.hhplus2weeks.domain.lecture.repository;

import com.example.hhplus2weeks.domain.lecture.Lecture;
import com.example.hhplus2weeks.infrastructure.entity.LectureEntity;

import java.util.List;

public interface LectureRepository {
    List<Lecture> findAllLectureList();
}
