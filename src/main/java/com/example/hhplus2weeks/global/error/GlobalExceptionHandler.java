package com.example.hhplus2weeks.global.error;

import com.example.hhplus2weeks.domain.lecture.exception.DuplicateRequestsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<ErrorResponse> handleException(Exception e) {
//        return ResponseEntity.status(500).body(new ErrorResponse("500", "에러가 발생했습니다."));
//    }

    @ExceptionHandler(value = DuplicateRequestsException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateException(DuplicateRequestsException e) {
        int code = HttpStatus.CONFLICT.value();
        return ResponseEntity.status(code).body(new ErrorResponse(String.valueOf(code), e.getMessage()));
    }
}
