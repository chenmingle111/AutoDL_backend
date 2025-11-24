package com.autodl_backend.local.exception;

/**
 * 镜像格式不支持异常
 * 当请求的镜像格式不被系统支持时抛出此异常
 */
public class UnsupportedImageFormatException extends RuntimeException {

    public UnsupportedImageFormatException(String message) {
        super(message);
    }

    public UnsupportedImageFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsupportedImageFormatException(String imageUuid, String format) {
        super("镜像格式不支持: UUID " + imageUuid + ", 格式: " + format);
    }
}
