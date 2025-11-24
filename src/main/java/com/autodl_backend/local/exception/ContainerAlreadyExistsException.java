package com.autodl_backend.local.exception;

/**
 * 容器已存在异常
 * 当尝试创建已存在的容器时抛出此异常
 */
public class ContainerAlreadyExistsException extends RuntimeException {


    public ContainerAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContainerAlreadyExistsException(String containerUuid) {
        super("容器已存在: UUID " + containerUuid);
    }
}
