package com.autodl_backend.local.exception;

/**
 * 容器状态异常
 * 当容器处于未知或异常状态时抛出此异常
 */
public class ContainerStatusException extends RuntimeException {

    public ContainerStatusException(String message) {
        super(message);
    }

    public ContainerStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContainerStatusException(String containerUuid, String status) {
        super("容器状态异常: UUID " + containerUuid + ", 状态: " + status);
    }
}
