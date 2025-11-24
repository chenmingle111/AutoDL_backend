package com.autodl_backend.local.exception;

/**
 * 镜像拉取失败异常
 * 当无法从镜像仓库拉取镜像时抛出此异常
 */
public class ImagePullFailedException extends RuntimeException {

    public ImagePullFailedException(String message) {
        super(message);
    }

    public ImagePullFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImagePullFailedException(String imageUuid, String reason) {
        super("镜像拉取失败: UUID " + imageUuid + ", 原因: " + reason);
    }
}
