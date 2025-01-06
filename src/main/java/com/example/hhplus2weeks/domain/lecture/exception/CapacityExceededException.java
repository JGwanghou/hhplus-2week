package com.example.hhplus2weeks.domain.lecture.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CapacityExceededException extends RuntimeException{
    public CapacityExceededException(String message) {
        super(message);
    }
}
