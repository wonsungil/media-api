package com.bbp.bbptest.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    FACEBOOK_ERROR_01(HttpStatus.BAD_REQUEST, "error");

    private final HttpStatus httpStatus;
    private final String message;
}
