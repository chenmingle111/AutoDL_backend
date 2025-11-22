package com.autodl_backend.common.exception;

import lombok.Getter;

@Getter
public class AutoDLException extends RuntimeException {
    private final String code;

    public AutoDLException(String code, String message) {
        super(message);
        this.code = code;
    }
}
