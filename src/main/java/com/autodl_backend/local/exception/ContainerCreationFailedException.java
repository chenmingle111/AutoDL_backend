package com.autodl_backend.local.exception;

/**
 * 容器创建失败异常
 * 当由于系统错误导致容器创建失败时抛出此异常
 */
public class ContainerCreationFailedException extends RuntimeException {

    public ContainerCreationFailedException(String message) {
        super(message);
    }

    public ContainerCreationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContainerCreationFailedException(String containerUuid, String reason) {
        super("容器创建失败: UUID " + containerUuid + ", 原因: " + reason);
    }
}
