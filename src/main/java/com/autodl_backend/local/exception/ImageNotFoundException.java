package com.autodl_backend.local.exception;

/**
 * 镜像不存在异常
 * 当尝试操作不存在的镜像时抛出此异常
 */
public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String message) {
        super(message);
    }

    public ImageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * 静态工厂方法，用于创建镜像UUID不存在的异常
     * @param imageUuid 镜像UUID
     * @return ImageNotFoundException实例
     */
    public static ImageNotFoundException forUuid(String imageUuid) {
        return new ImageNotFoundException("镜像不存在: UUID " + imageUuid);
    }
}
