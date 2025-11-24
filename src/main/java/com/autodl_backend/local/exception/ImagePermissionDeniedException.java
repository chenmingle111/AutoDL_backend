package com.autodl_backend.local.exception;

/**
 * 镜像权限问题异常
 * 当用户无权访问请求的镜像时抛出此异常
 */
public class ImagePermissionDeniedException extends RuntimeException {

    public ImagePermissionDeniedException(String message) {
        super(message);
    }

    public ImagePermissionDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImagePermissionDeniedException(Integer userId, String imageUuid) {
        super("用户无权访问镜像: 用户ID " + userId + ", 镜像UUID " + imageUuid);
    }
}
