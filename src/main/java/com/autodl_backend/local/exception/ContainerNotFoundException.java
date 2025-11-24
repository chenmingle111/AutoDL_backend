package com.autodl_backend.local.exception;

/**
 * 容器不存在异常
 * 当尝试操作不存在的容器时抛出此异常
 */
public class ContainerNotFoundException extends RuntimeException {


    public ContainerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContainerNotFoundException(String containerUuid) {
        super("容器不存在: UUID " + containerUuid);
    }
}
