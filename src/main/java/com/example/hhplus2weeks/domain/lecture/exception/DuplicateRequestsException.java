package com.example.hhplus2weeks.domain.lecture.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateRequestsException extends RuntimeException{
    public DuplicateRequestsException(String message) {
        super(message);
    }
}
