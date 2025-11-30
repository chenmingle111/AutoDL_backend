package com.autodl_backend.util.AutodlFataConverter;

import com.autodl_backend.autodl.dto.container.*;
import com.autodl_backend.local.pojo.entity.Containers;
import com.autodl_backend.local.pojo.enums.ContainerStatus;
import com.autodl_backend.util.DateTimeHelper;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * AutoDL容器数据转换工具类
 * 用于将AutoDL API返回的DTO对象转换为本地实体对象
 */
@Slf4j
public class ContainerConverter {


    //获取容器列表
    /**
     * 获取容器列表请求转换为Containers实体
     * @param req 容器列表请求
     * @return Containers实体
     */
    public static Containers convertToContainer(ContainerListReq req) {
        Containers container = new Containers();

        // 设置基本信息
        container.setDeploymentUuid(req.getDeploymentUuid());
        if (req.getContainerUuid() != null) {
            container.setContainerUuid(req.getContainerUuid());
        } else {
            // 如果没有提供container_uuid，生成一个默认值
            container.setContainerUuid("default-container-uuid-" + System.currentTimeMillis());
        }

        // 设置machine_uuid为默认值，使用一个已知存在的值
        container.setMachineUuid("ff6c479d90");

        // 设置其他属性
        if (req.getGpuName() != null) {
            container.setGpuName(req.getGpuName());
        }

        // 设置必需的默认值，满足数据库约束
        container.setGpuNum(1); // GPU数量默认为1
        container.setCpuNum(1); // CPU核心数默认为1
        container.setMemorySize(1024L * 1024 * 1024); // 内存默认为1GB
        container.setPrice(1000); // 价格默认为1元/小时（1000是因为存储单位是元*1000）
        container.setImageUuid("image-d8ccae7a70"); // 使用已知存在的镜像UUID

        // 设置初始状态
        container.setStatus(ContainerStatus.RUNNING);
        container.setReleased(false);

        return container;
    }

    /**
     * 将获取容器列表响应转换为Container实体
     * @param data 容器列表响应
     * @param containers 原始容器实体
     * @return 更新后的容器实体
     */
    public static Containers convertToContainer(ContainerListData data, Containers containers) {
        if (data != null && data.getList()!= null && !data.getList().isEmpty()) {
            for (ContainerListItem containerListItem : data.getList()) {
                // 设置container_uuid，确保不为空
                if (containerListItem.getUuid() != null && !containerListItem.getUuid().isEmpty()) {
                    containers.setContainerUuid(containerListItem.getUuid());
                }

                containers.setDeploymentUuid(containerListItem.getDeploymentUuid());

                // 设置machine_uuid，确保不为空
                if (containerListItem.getMachineId() != null && !containerListItem.getMachineId().isEmpty()) {
                    containers.setMachineUuid(containerListItem.getMachineId());
                } else {
                    containers.setMachineUuid("ff6c479d90");
                }

                // 转换状态
                if (containerListItem.getStatus() != null) {
                    try {
                        containers.setStatus(containerListItem.getStatus());
                    } catch (IllegalArgumentException e) {
                        log.warn("Unknown container status: {}, using default", containerListItem.getStatus());
                        containers.setStatus(ContainerStatus.CREATING);
                    }
                }

                // 设置其他属性
                containers.setGpuName(containerListItem.getGpuName());
                containers.setGpuNum(containerListItem.getGpuNum());
                containers.setCpuNum(containerListItem.getCpuNum());
                containers.setMemorySize(containerListItem.getMemorySize());
                containers.setImageUuid(containerListItem.getImageUuid());

                if (containerListItem.getPrice() != null) {
                    containers.setPrice(Math.round(containerListItem.getPrice()));
                }

                // 设置容器信息
                if (containerListItem.getInfo() != null) {
                    ContainerInfo info = containerListItem.getInfo();
                    containers.setSshCommand(info.getSshCommand());
                    containers.setSshPassword(info.getRootPassword());

                    // 设置服务URL（优先使用6006端口，如果没有则使用6008端口）
                    if (info.getService6006PortUrl() != null) {
                        containers.setServiceUrl(info.getService6006PortUrl());
                    } else if (info.getService6008PortUrl() != null) {
                        containers.setServiceUrl(info.getService6008PortUrl());
                    }
                }

                // 转换时间
                if (containerListItem.getStartedAt() != null) {
                    try {
                        containers.setStartedAt(DateTimeHelper.parseDateTime(containerListItem.getStartedAt()));
                    } catch (Exception e) {
                        log.warn("Failed to parse started_at: {}", containerListItem.getStartedAt());
                        containers.setStartedAt(LocalDateTime.now());
                    }
                }

                if (containerListItem.getStoppedAt() != null) {
                    try {
                        containers.setStoppedAt(DateTimeHelper.parseDateTime(containerListItem.getStoppedAt()));
                    } catch (Exception e) {
                        log.warn("Failed to parse stopped_at: {}", containerListItem.getStoppedAt());
                        // 不设置stoppedAt，保持为null
                    }
                }

                if (containerListItem.getCreatedAt() != null) {
                    try {
                        containers.setCreatedAt(DateTimeHelper.parseDateTime(containerListItem.getCreatedAt()));
                    } catch (Exception e) {
                        log.warn("Failed to parse created_at: {}", containerListItem.getCreatedAt());
                        containers.setCreatedAt(LocalDateTime.now());
                    }
                }

                if (containerListItem.getUpdatedAt() != null) {
                    try {
                        containers.setUpdatedAt(DateTimeHelper.parseDateTime(containerListItem.getUpdatedAt()));
                    } catch (Exception e) {
                        log.warn("Failed to parse updated_at: {}", containerListItem.getUpdatedAt());
                        containers.setUpdatedAt(LocalDateTime.now());
                    }
                }
            }
        }
        return containers;
    }

    //停止容器
    /**
     * 将停止容器请求转换为Containers实体
     * @param req 停止容器请求
     * @param existingContainer 已存在的容器实体
     * @return 更新后的容器实体
     */
    public static Containers convertToContainer(ContainerStopReq req, Containers existingContainer) {
        if (existingContainer == null) {
            return null;
        }

        // 设置容器状态为已停止
        existingContainer.setStatus(ContainerStatus.SHUTDOWN);
        existingContainer.setStoppedAt(LocalDateTime.now());

        // 设置是否不缓存容器
        if (req.getNoCache() != null && req.getNoCache()) {
            existingContainer.setReleased(true);
        }

        return existingContainer;
    }

    //获取容器事件
    /**
     * 将容器事件请求转换为Containers实体
     * @param req 容器事件请求
     * @return Containers实体
     */
    public static Containers convertToContainer(ContainerEventsReq req) {
        Containers container = new Containers();

        // 设置基本信息
        container.setDeploymentUuid(req.getDeploymentUuid());
        if (req.getDeploymentContainerUuid() != null) {
            container.setContainerUuid(req.getDeploymentContainerUuid());
        } else {
            // 如果没有提供container_uuid，生成一个默认值
            container.setContainerUuid("default-container-uuid-" + System.currentTimeMillis());
        }

        // 设置machine_uuid为默认值，使用一个已知存在的值
        container.setMachineUuid("ff6c479d90");

        // 设置必需的默认值，满足数据库约束
        container.setGpuNum(1); // GPU数量默认为1
        container.setCpuNum(1); // CPU核心数默认为1
        container.setMemorySize(1024L * 1024 * 1024); // 内存默认为1GB
        container.setPrice(1000); // 价格默认为1元/小时（1000是因为存储单位是元*1000）
        container.setImageUuid("image-d8ccae7a70"); // 使用已知存在的镜像UUID

        // 设置初始状态
        container.setStatus(ContainerStatus.RUNNING);
        container.setReleased(false);

        return container;
    }

    /**
     * 将容器事件项转换为Containers实体
     */
    public static Containers convertToContainer(ContainerEventData data, Containers containers) {
        if (data != null && data.getList() != null && !data.getList().isEmpty()) {
            for (ContainerEventItem item : data.getList()) {
                // 使用传入的容器对象，而不是创建新对象，这样能保留deploymentUuid等信息
                if (containers != null) {
                    // 设置基本信息
                    containers.setContainerUuid(item.getDeploymentContainerUuid());

                    // 确保machine_uuid不为空
                    if (containers.getMachineUuid() == null || containers.getMachineUuid().isEmpty()) {
                        containers.setMachineUuid("ff6c479d90");
                    }

                    // 转换状态
                    if (item.getStatus() != null) {
                        try {
                            containers.setStatus(item.getStatus());
                        } catch (IllegalArgumentException e) {
                            log.warn("Unknown container status: {}, using default", item.getStatus());
                            containers.setStatus(ContainerStatus.SHUTDOWN);
                        }
                    }

                    // 转换时间
                    if (item.getCreatedAt() != null) {
                        try {
                            containers.setCreatedAt(DateTimeHelper.parseDateTime(item.getCreatedAt()));
                        } catch (Exception e) {
                            log.warn("Failed to parse created_at: {}", item.getCreatedAt());
                            containers.setCreatedAt(LocalDateTime.now());
                        }
                    }

                    return containers;
                }
            }
        }
        return containers;
    }



}
