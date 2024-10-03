package com.example.hhplus2weeks.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "lecture_schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class LectureScheduleEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private LectureEntity lecture;

    @Column(nullable = false)
    private String speaker;

    @Column(nullable = false)
    private int registerCount = 0;

    @Column(nullable = false)
    private int registerMaxCount = 30;

    @Column(nullable = false)
    private LocalDateTime lectureDateTime;
}
