package com.example.hhplus2weeks.domain.lecture.repository;

import com.example.hhplus2weeks.domain.lecture.Lecture;
import com.example.hhplus2weeks.infrastructure.entity.LectureEntity;

import java.util.List;
import java.util.Optional;

public interface LectureRepository {
    List<Lecture> findAllLectureList();
    Optional<Lecture> getReferenceById(Long userId);
}
