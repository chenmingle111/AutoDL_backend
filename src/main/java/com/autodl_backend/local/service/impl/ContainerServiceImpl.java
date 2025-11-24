package com.autodl_backend.local.service.impl;

import com.autodl_backend.autodl.client.AutoDLClient;
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
import com.autodl_backend.local.mapper.ContainersMapper;
import com.autodl_backend.local.pojo.entity.Containers;
import com.autodl_backend.local.service.ContainerService;
import com.autodl_backend.util.ContainerValidationUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public Containers getContainerByUuid(String containerUuid) throws ContainerNotFoundException {
        if (!StringUtils.hasText(containerUuid)) {
            throw new ContainerNotFoundException("容器UUID不能为空");
        }

        QueryWrapper<Containers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("container_uuid", containerUuid)
                   .eq("is_deleted", 0);

        Containers container = getOne(queryWrapper);
        ContainerValidationUtils.checkContainerExists(container);

        return container;
    }

    @Override
    public void checkContainerExists(String containerUuid) throws ContainerAlreadyExistsException {
        if (!StringUtils.hasText(containerUuid)) {
            return; // 如果UUID为空，则不存在，无需检查
        }

        QueryWrapper<Containers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("container_uuid", containerUuid)
                   .eq("is_deleted", 0);

        Containers container = getOne(queryWrapper);
        ContainerValidationUtils.checkContainerAlreadyExists(container);
    }

    @Override
    public Containers checkContainerCanStart(String containerUuid) 
            throws ContainerNotFoundException, ContainerStartFailedException, ContainerStatusException {
        Containers container = getContainerByUuid(containerUuid);
        ContainerValidationUtils.checkContainerCanStart(container);

        return container;
    }

    @Override
    public Containers checkContainerCanStop(String containerUuid) 
            throws ContainerNotFoundException, ContainerStopFailedException, ContainerStatusException {
        Containers container = getContainerByUuid(containerUuid);
        ContainerValidationUtils.checkContainerCanStop(container);

        return container;
    }
}
