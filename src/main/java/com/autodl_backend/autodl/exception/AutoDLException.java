package com.autodl_backend.autodl.exception;

/**
 * Custom exception for AutoDL API errors.
 */
public class AutoDLException extends RuntimeException {

    private String code;

    public AutoDLException(String message) {
        super(message);
    }

    public AutoDLException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
