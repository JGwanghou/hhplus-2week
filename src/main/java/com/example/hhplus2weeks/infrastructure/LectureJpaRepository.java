package com.example.hhplus2weeks.infrastructure;

import com.example.hhplus2weeks.infrastructure.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {
    @Override
    Optional<LectureEntity> findById(Long userId);
}
