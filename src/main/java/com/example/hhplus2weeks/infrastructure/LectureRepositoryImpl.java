package com.example.hhplus2weeks.infrastructure;

import com.example.hhplus2weeks.domain.lecture.Lecture;
import com.example.hhplus2weeks.domain.lecture.repository.LectureRepository;
import com.example.hhplus2weeks.infrastructure.entity.LectureEntity;
import com.example.hhplus2weeks.infrastructure.mapper.LectureMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {
    private final LectureJpaRepository lectureJpaRepository;


    @Override
    public List<Lecture> findAllLectureList() {
        return lectureJpaRepository.findAll().stream()
                .map(LectureMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Lecture> getReferenceById(Long id) {
        return Optional.of(LectureMapper.toDomain(lectureJpaRepository.getReferenceById(id)));
    }
}
