package com.autodl_backend.local.exception;

/**
 * 用户被禁用异常
 * 当尝试操作被禁用的用户时抛出此异常
 */
public class UserDisabledException extends RuntimeException {

    public UserDisabledException(String message) {
        super(message);
    }

    public UserDisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDisabledException() {
        super("用户账户已被管理员禁用");
    }
}
