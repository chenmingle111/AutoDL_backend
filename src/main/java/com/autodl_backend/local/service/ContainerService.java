package com.autodl_backend.local.service;

import com.autodl_backend.autodl.dto.container.ContainerEventsReq;
import com.autodl_backend.autodl.dto.container.ContainerListReq;
import com.autodl_backend.autodl.dto.container.ContainerStopReq;
import com.autodl_backend.autodl.dto.container.ContainerEventData;
import com.autodl_backend.autodl.dto.container.ContainerListData;
import com.autodl_backend.local.exception.ContainerAlreadyExistsException;
import com.autodl_backend.local.exception.ContainerNotFoundException;
import com.autodl_backend.local.exception.ContainerStartFailedException;
import com.autodl_backend.local.exception.ContainerStatusException;
import com.autodl_backend.local.exception.ContainerStopFailedException;
import com.autodl_backend.local.pojo.entity.Containers;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Service interface for container operations.
 */
public interface ContainerService extends IService<Containers> {

    /**
     * Query container events
     */
    ContainerEventData getContainerEvents(ContainerEventsReq req);

    /**
     * Query container list
     */
    ContainerListData getContainerList(ContainerListReq req);

    /**
     * Stop a container
     */
    Object stopContainer(ContainerStopReq req);

    /**
     * 根据UUID获取容器
     * 
     * @param containerUuid 容器UUID
     * @return 容器对象
     * @throws ContainerNotFoundException 如果容器不存在
     */
    Containers getContainerByUuid(String containerUuid) throws ContainerNotFoundException;

    /**
     * 检查容器是否已存在
     * 
     * @param containerUuid 容器UUID
     * @throws ContainerAlreadyExistsException 如果容器已存在
     */
    void checkContainerExists(String containerUuid) throws ContainerAlreadyExistsException;

    /**
     * 检查容器是否可以启动
     * 
     * @param containerUuid 容器UUID
     * @return 容器对象
     * @throws ContainerNotFoundException 如果容器不存在
     * @throws ContainerStartFailedException 如果容器无法启动
     * @throws ContainerStatusException 如果容器状态异常
     */
    Containers checkContainerCanStart(String containerUuid) 
        throws ContainerNotFoundException, ContainerStartFailedException, ContainerStatusException;

    /**
     * 检查容器是否可以停止
     * 
     * @param containerUuid 容器UUID
     * @return 容器对象
     * @throws ContainerNotFoundException 如果容器不存在
     * @throws ContainerStopFailedException 如果容器无法停止
     * @throws ContainerStatusException 如果容器状态异常
     */
    Containers checkContainerCanStop(String containerUuid) 
        throws ContainerNotFoundException, ContainerStopFailedException, ContainerStatusException;
}
