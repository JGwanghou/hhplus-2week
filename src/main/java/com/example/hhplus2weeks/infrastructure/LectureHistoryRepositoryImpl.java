package com.example.hhplus2weeks.infrastructure;

import com.example.hhplus2weeks.domain.lecture.LectureHistory;
import com.example.hhplus2weeks.domain.lecture.LectureSchedule;
import com.example.hhplus2weeks.domain.lecture.repository.LectureHistoryRepository;
import com.example.hhplus2weeks.infrastructure.entity.LectureHistoryEntity;
import com.example.hhplus2weeks.infrastructure.entity.LectureScheduleEntity;
import com.example.hhplus2weeks.infrastructure.mapper.LectureHistoryMapper;
import com.example.hhplus2weeks.infrastructure.mapper.LectureScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LectureHistoryRepositoryImpl implements LectureHistoryRepository {
    private final LectureHistoryJpaRepository lectureHistoryJpaRepository;

    @Override
    public LectureHistory save(LectureHistory lectureHistory) {
        return LectureHistoryMapper.toDomain(
                lectureHistoryJpaRepository.save(
                        LectureHistoryMapper.toEntity(lectureHistory)
                )
        );
    }

    @Override
    public Optional<LectureHistory> findLectureHistoryByLectureScheduleAndUserId(LectureSchedule lectureSchedule, Long userId) {
        LectureScheduleEntity lectureScheduleEntity = LectureScheduleMapper.toEntity(lectureSchedule);
        Optional<LectureHistoryEntity> optionalLectureHistoryEntity = lectureHistoryJpaRepository.findLectureHistoryByLectureScheduleAndUserId(lectureScheduleEntity, userId);
        return optionalLectureHistoryEntity.map(LectureHistoryMapper::toDomain);
    }

}
