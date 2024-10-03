package com.example.hhplus2weeks.domain.lecture.service;

import com.example.hhplus2weeks.domain.lecture.Lecture;
import com.example.hhplus2weeks.domain.lecture.LectureHistory;
import com.example.hhplus2weeks.domain.lecture.LectureSchedule;
import com.example.hhplus2weeks.domain.lecture.exception.DuplicateRequestsException;
import com.example.hhplus2weeks.domain.lecture.repository.LectureHistoryRepository;
import com.example.hhplus2weeks.domain.lecture.repository.LectureScheduleRepository;
import com.example.hhplus2weeks.infrastructure.mapper.LectureHistoryMapper;
import com.example.hhplus2weeks.infrastructure.mapper.LectureScheduleMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LectureServiceTest {

    @InjectMocks
    private LectureService lectureService;

    @Mock
    private LectureScheduleRepository lectureScheduleRepository;

    @Mock
    private LectureHistoryRepository lectureHistoryRepository;

    @Mock
    private LectureApplyValid lectureApplyValid;

    private LectureSchedule lectureSchedule1;
    private LectureHistory lectureHistory;

    @BeforeEach
    void setup() {
        Lecture testLecture = Lecture.create(1L, "신나는 MSA 수업");
        lectureSchedule1 = LectureSchedule.create(
                1L,
                testLecture,
                "김강연자",
                0,
                30,
                LocalDateTime.of(2024, 10, 12, 10, 00)
        );

        lectureHistory = LectureHistory.create(1L, lectureSchedule1, 29L);
    }


    @Test
    @DisplayName("특강 신청")
    void 단순_특강신청() {
        Long lectureSche = 1L;
        Long userId = 29L;

        when(lectureScheduleRepository.lockFindByLectureScheduleId(lectureSche)).thenReturn(lectureSchedule1);
        when(lectureApplyValid.isApplyCheck(lectureSchedule1, userId)).thenReturn(false);

        LectureSchedule lectureSchedule = lectureService.lectureScheduleApply(lectureSche, userId);

        assertEquals(lectureSchedule.getRegisterCount(), 1);
        verify(lectureHistoryRepository).save(any());
    }


    @Test
    @DisplayName("중복 특강 신청")
    void 중복_특강신청_예외발생() {
        Long lectureSche = 1L;
        Long userId = 29L;

        when(lectureScheduleRepository.lockFindByLectureScheduleId(lectureSche)).thenReturn(lectureSchedule1);
        when(lectureApplyValid.isApplyCheck(lectureSchedule1, userId)).thenReturn(true);

        DuplicateRequestsException exception = assertThrows(
                DuplicateRequestsException.class, () -> lectureService.lectureScheduleApply(lectureSche, userId)
        );

        assertEquals("이미 신청된 강의입니다.", exception.getMessage());
    }
}