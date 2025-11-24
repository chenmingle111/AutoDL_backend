package com.autodl_backend.local.service.impl;

import com.autodl_backend.autodl.client.AutoDLClient;
import com.autodl_backend.autodl.dto.container.ContainerEventsReq;
import com.autodl_backend.autodl.dto.container.ContainerListReq;
import com.autodl_backend.autodl.dto.container.ContainerStopReq;
import com.autodl_backend.autodl.dto.container.ContainerEventData;
import com.autodl_backend.autodl.dto.container.ContainerListData;
import com.autodl_backend.local.mapper.ContainersMapper;
import com.autodl_backend.local.pojo.entity.Containers;
import com.autodl_backend.local.service.ContainerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of ContainerService.
 */
@Service
public class ContainerServiceImpl extends ServiceImpl<ContainersMapper, Containers> implements ContainerService {

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
