package com.autodl_backend.local.service;

import com.autodl_backend.autodl.dto.container.ContainerEventsReq;
import com.autodl_backend.autodl.dto.container.ContainerListReq;
import com.autodl_backend.autodl.dto.container.ContainerStopReq;
import com.autodl_backend.autodl.dto.container.ContainerEventData;
import com.autodl_backend.autodl.dto.container.ContainerListData;
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

}
