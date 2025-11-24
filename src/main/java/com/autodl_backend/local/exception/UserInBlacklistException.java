package com.autodl_backend.local.exception;

/**
 * 用户在黑名单中异常
 * 当尝试操作在调度黑名单中的用户时抛出此异常
 */
public class UserInBlacklistException extends RuntimeException {

    public UserInBlacklistException(String message) {
        super(message);
    }

    public UserInBlacklistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserInBlacklistException() {
        super("用户在调度黑名单中，无法执行此操作");
    }
}
