package com.bbp.bbptest.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public ResponseEntity<ErrorResponse> toResponseEntity() {
        HttpStatus httpStatus = HttpStatus.valueOf(status);
        return ResponseEntity
                .status(httpStatus)
                .body(ErrorResponse
                              .builder()
                              .status(httpStatus.value())
                              .error(httpStatus.name())
                              .code(code)
                              .message(message)
                              .build()
                );
    }
}
