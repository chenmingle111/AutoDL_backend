package com.autodl_backend.local.exception;

/**
 * 容器启动失败异常
 * 当容器已创建但启动过程中出错时抛出此异常
 */
public class ContainerStartFailedException extends RuntimeException {

    public ContainerStartFailedException(String message) {
        super(message);
    }

    public ContainerStartFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContainerStartFailedException(String containerUuid, String reason) {
        super("容器启动失败: UUID " + containerUuid + ", 原因: " + reason);
    }
}
