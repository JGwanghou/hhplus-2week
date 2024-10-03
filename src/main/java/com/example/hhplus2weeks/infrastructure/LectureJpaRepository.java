package com.example.hhplus2weeks.infrastructure;

import com.example.hhplus2weeks.infrastructure.entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {
}
