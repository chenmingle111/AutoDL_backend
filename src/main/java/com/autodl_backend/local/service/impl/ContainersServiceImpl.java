package com.autodl_backend.local.service.impl;

import com.autodl_backend.autodl.client.AutoDLClient;
import com.autodl_backend.autodl.dto.container.*;
import com.autodl_backend.local.mapper.ContainersMapper;
import com.autodl_backend.local.pojo.entity.Containers;
import com.autodl_backend.local.pojo.entity.Deployments;
import com.autodl_backend.local.service.ContainerService;
import com.autodl_backend.util.AutodlFataConverter.ContainerConverter;
import com.autodl_backend.util.AutodlFataConverter.DeploymentConverter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Containers Service Implementation
 */
@Slf4j
@Service
public class ContainersServiceImpl extends ServiceImpl<ContainersMapper, Containers> implements ContainerService {

    @Autowired
    private AutoDLClient autoDLClient;

    @Override
    public ContainerEventData getContainerEvents(ContainerEventsReq req) {
        // 从请求中获取uid
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        String uid = (String) request.getAttribute("uid");

        // 验证部署是否正确
        if (req.getDeploymentUuid() == null || req.getDeploymentUuid().isEmpty()) {
            throw new RuntimeException("deploymentUuid为空");
        }


        // 将请求转换为部署实体
        Containers container = ContainerConverter.convertToContainer(req);

        // 调用AutoDL客户端获取容器列表
        ContainerEventData containerEvents = autoDLClient.getContainerEvents(req);

        // 如果获取到数据，将其转换为Containers实体并保存到数据库
        if (containerEvents != null && containerEvents.getList() != null) {
            for (ContainerEventItem containerEventItem : containerEvents.getList()) {
                // 先查询是否已存在该deploymentUuid的记录
                Containers existingContainer = getOne(
                        new QueryWrapper<Containers>()
                                .eq("container_uuid",containerEventItem.getDeploymentContainerUuid())
                );

                if (existingContainer == null) {
                    // 不存在，则转换并保存
                    // 使用转换器将容器列表转换为实体列表
                    Containers containers = ContainerConverter.convertToContainer(containerEvents, container);
                    save(containers);
                }
            }
        }

        // 记录用户操作日志
        log.info("用户[{}]正在获取容器事件", uid);

        // 调用AutoDL客户端获取容器事件
        return containerEvents;
    }

    @Override
    public ContainerListData getContainerList(ContainerListReq req) {
        // 从请求中获取uid
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        String uid = (String) request.getAttribute("uid");

        // 验证请求参数
        if (req.getDeploymentUuid() == null || req.getDeploymentUuid().isEmpty()) {
            throw new RuntimeException("deploymentUuid为空");
        }


        // 记录用户操作日志
        log.info("用户[{}]正在获取部署[{}]的容器列表", uid, req.getDeploymentUuid());

        // 将请求转换为部署实体
        Containers container = ContainerConverter.convertToContainer(req);

        // 调用AutoDL客户端获取容器列表
        ContainerListData containerList = autoDLClient.getContainerList(req);

        // 如果获取到数据，将其转换为Containers实体并保存到数据库
        if (containerList != null && containerList.getList() != null) {
            for (ContainerListItem containerListItem : containerList.getList()) {
                // 先查询是否已存在该deploymentUuid的记录
                Containers existingContainer = getOne(
                        new QueryWrapper<Containers>()
                                .eq("container_uuid",containerListItem.getUuid())
                );

                if (existingContainer == null) {
                    // 不存在，则转换并保存
                    // 使用转换器将容器列表转换为实体列表
                    Containers containers = ContainerConverter.convertToContainer(containerList, container);
                    save(containers);
                }
            }
        }


        // 记录日志
        log.info("用户[{}]成功获取部署[{}]的容器列表，共[{}]个容器",
                uid, req.getDeploymentUuid(), containerList.getList().size());

        return containerList;
    }

    @Override
    public Object stopContainer(ContainerStopReq req) {
        // 从请求中获取uid
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        String uid = (String) request.getAttribute("uid");

        // 验证请求参数
        if (req.getDeploymentContainerUuid() == null || req.getDeploymentContainerUuid().isEmpty()) {
            throw new RuntimeException("deploymentContainerUuid为空");
        }

        // 记录用户操作日志
        log.info("用户[{}]正在停止容器[{}]", uid, req.getDeploymentContainerUuid());

        // 获取现有容器
        Containers container = getOne(
            new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<Containers>()
                .eq("container_uuid", req.getDeploymentContainerUuid())
        );
        if (container == null) {
            throw new RuntimeException("容器不存在");
        }
        // 使用转换器将停止请求转换为容器实体
        container = ContainerConverter.convertToContainer(req, container);

        // 调用AutoDL客户端停止容器
        Object response = autoDLClient.stopContainer(req);

        // 更新本地数据库中的容器状态
        updateById(container);

        // 记录日志
        log.info("用户[{}]成功停止容器[{}]", uid, req.getDeploymentContainerUuid());

        return response;
    }

}
