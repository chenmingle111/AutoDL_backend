package com.autodl_backend.util;

import com.autodl_backend.local.exception.ContainerAlreadyExistsException;
import com.autodl_backend.local.exception.ContainerCreationFailedException;
import com.autodl_backend.local.exception.ContainerNotFoundException;
import com.autodl_backend.local.exception.ContainerStartFailedException;
import com.autodl_backend.local.exception.ContainerStatusException;
import com.autodl_backend.local.exception.ContainerStopFailedException;
import com.autodl_backend.local.pojo.entity.Containers;
import com.autodl_backend.local.pojo.enums.ContainerStatus;
import org.springframework.util.StringUtils;

/**
 * 容器验证工具类
 * 提供容器相关的验证方法
 */
public class ContainerValidationUtils {

    /**
     * 检查容器是否存在
     */
    public static void checkContainerExists(Containers container) throws ContainerNotFoundException {
        if (container == null) {
            throw new ContainerNotFoundException("容器对象不能为空");
        }

        if (container.getIsDeleted() == 1) {
            throw new ContainerNotFoundException(container.getContainerUuid());
        }
    }

    /**
     * 检查容器是否已存在
     */
    public static void checkContainerAlreadyExists(Containers container) throws ContainerAlreadyExistsException {
        if (container != null && container.getIsDeleted() == 0) {
            throw new ContainerAlreadyExistsException(container.getContainerUuid());
        }
    }

    /**
     * 检查容器状态是否可以启动
     */
    public static void checkContainerCanStart(Containers container) throws ContainerStartFailedException, ContainerStatusException {
        if (container == null) {
            throw new ContainerStartFailedException("容器对象不能为空");
        }

        if (container.getStatus() == ContainerStatus.RUNNING) {
            throw new ContainerStartFailedException(container.getContainerUuid(), "容器已经在运行中");
        }

        if (container.getStatus() == ContainerStatus.ERROR) {
            throw new ContainerStatusException(container.getContainerUuid(), "错误状态");
        }
    }

    /**
     * 检查容器状态是否可以停止
     */
    public static void checkContainerCanStop(Containers container) throws ContainerStopFailedException, ContainerStatusException {
        if (container == null) {
            throw new ContainerStopFailedException("容器对象不能为空");
        }

        if (container.getStatus() == ContainerStatus.SHUTDOWN) {
            throw new ContainerStopFailedException(container.getContainerUuid(), "容器已经停止");
        }

        if (container.getStatus() == ContainerStatus.ERROR) {
            throw new ContainerStatusException(container.getContainerUuid(), "错误状态");
        }
    }

    /**
     * 检查容器状态是否正常
     */
    public static void checkContainerStatus(Containers container) throws ContainerStatusException {
        if (container == null) {
            throw new ContainerStatusException("容器对象不能为空");
        }

        if (container.getStatus() == ContainerStatus.ERROR) {
            throw new ContainerStatusException(container.getContainerUuid(), "错误状态");
        }
    }

    /**
     * 检查容器UUID是否有效
     */
    public static void checkContainerUuid(String containerUuid) throws ContainerCreationFailedException {
        if (!StringUtils.hasText(containerUuid)) {
            throw new ContainerCreationFailedException("容器UUID不能为空");
        }
    }
}
