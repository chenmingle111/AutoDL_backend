package com.autodl_backend.local.service.impl;

import com.autodl_backend.autodl.client.AutoDLClient;
import com.autodl_backend.autodl.dto.container.ContainerEventsReq;
import com.autodl_backend.autodl.dto.container.ContainerListReq;
import com.autodl_backend.autodl.dto.container.ContainerStopReq;
import com.autodl_backend.autodl.dto.container.ContainerEventData;
import com.autodl_backend.autodl.dto.container.ContainerListData;
import com.autodl_backend.local.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of ContainerService.
 */
@Service
public class ContainerServiceImpl implements ContainerService {

    @Autowired
    private AutoDLClient autoDLClient;

    @Override
    public ContainerEventData getContainerEvents(ContainerEventsReq req) {
        return autoDLClient.getContainerEvents(req);
    }

    @Override
    public ContainerListData getContainerList(ContainerListReq req) {
        return autoDLClient.getContainerList(req);
    }

    @Override
    public Object stopContainer(ContainerStopReq req) {
        return autoDLClient.stopContainer(req);
    }
}
