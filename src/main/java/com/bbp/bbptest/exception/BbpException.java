package com.bbp.bbptest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BbpException extends RuntimeException{
    private final ErrorCode errorCode;
}
