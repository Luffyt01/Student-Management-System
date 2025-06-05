package com.example.student_management.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private LocalDateTime timeStamp;
    private HttpStatus status;
    private String error;

    public ApiError() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String error) {
        this();
        this.status = status;
        this.error = error;
    }
}
