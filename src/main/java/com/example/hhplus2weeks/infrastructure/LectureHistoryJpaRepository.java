package com.example.hhplus2weeks.infrastructure;

import com.example.hhplus2weeks.infrastructure.entity.LectureHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureHistoryJpaRepository extends JpaRepository<LectureHistoryEntity, Long> {
}
