package com.example.hhplus2weeks.domain.lecture;

import lombok.Getter;

@Getter
public class Lecture {
    private final Long id;
    private final String title;

    public Lecture(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public static Lecture create(Long id, String title) {
        return new Lecture(id, title);
    }
}
