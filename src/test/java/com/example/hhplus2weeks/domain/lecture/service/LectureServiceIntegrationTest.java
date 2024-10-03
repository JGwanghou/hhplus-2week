package com.example.hhplus2weeks.domain.lecture.service;

import com.example.hhplus2weeks.domain.lecture.LectureSchedule;
import com.example.hhplus2weeks.domain.lecture.exception.DuplicateRequestsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LectureServiceIntegrationTest {

    @Autowired
    private LectureService lectureService;


    @Test
    @DisplayName("[통합테스트] 동일한 유저 정보로 같은 특강을 5번 신청했을 때, 1번만 성공한다.")
    public void test1() throws ExecutionException, InterruptedException {
        int threadCount = 5;

        Long scheId = 1L;
        Long userId = 20L;

        List<CompletableFuture<LectureSchedule>> futures = new ArrayList<>();
        List<LectureSchedule> successResults = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            CompletableFuture<LectureSchedule> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return lectureService.lectureScheduleApply(scheId, userId);
                } catch (DuplicateRequestsException e) {
                    return null;
                }
            });

            futures.add(future);
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        for (CompletableFuture<LectureSchedule> future : futures) {

            LectureSchedule result = future.get();
            // 성공한 경우만 저장
            if (result != null) {
                successResults.add(result);
            }
        }

        assertEquals(1, successResults.size());
    }
}