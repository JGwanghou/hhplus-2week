package com.example.hhplus2weeks.infrastructure;

import com.example.hhplus2weeks.domain.lecture.LectureHistory;
import com.example.hhplus2weeks.domain.lecture.repository.LectureHistoryRepository;
import com.example.hhplus2weeks.infrastructure.entity.LectureHistoryEntity;
import com.example.hhplus2weeks.infrastructure.mapper.LectureHistoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LectureHistoryRepositoryImpl implements LectureHistoryRepository {
    private final LectureHistoryJpaRepository lectureHistoryJpaRepository;

    @Override
    public LectureHistory save(LectureHistory lectureHistory) {
        return LectureHistoryMapper.toDomain(lectureHistoryJpaRepository.save(LectureHistoryMapper.toEntity(lectureHistory)));
    }
}
