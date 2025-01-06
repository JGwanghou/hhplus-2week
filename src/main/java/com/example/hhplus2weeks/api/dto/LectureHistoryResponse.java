package com.example.hhplus2weeks.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LectureHistoryResponse {
    private Long lectureId;
    private String lectureTitle;
    private String speaker;
}
