package com.autodl_backend.local.exception;

/**
 * 容器停止失败异常
 * 当尝试停止容器时发生错误时抛出此异常
 */
public class ContainerStopFailedException extends RuntimeException {

    public ContainerStopFailedException(String message) {
        super(message);
    }

    public ContainerStopFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContainerStopFailedException(String containerUuid, String reason) {
        super("容器停止失败: UUID " + containerUuid + ", 原因: " + reason);
    }
}
