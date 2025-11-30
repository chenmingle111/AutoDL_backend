package com.autodl_backend.local.service.impl;

import com.autodl_backend.autodl.client.AutoDLClient;
import com.autodl_backend.autodl.dto.deployment.*;
import com.autodl_backend.local.mapper.DeploymentsMapper;
import com.autodl_backend.local.mapper.ImagesMapper;
import com.autodl_backend.local.pojo.entity.Containers;
import com.autodl_backend.local.pojo.entity.Deployments;
import com.autodl_backend.local.pojo.entity.Images;
import com.autodl_backend.local.pojo.enums.DeploymentStatus;
import com.autodl_backend.local.service.DeploymentsService;
import com.autodl_backend.util.AutodlFataConverter.DeploymentConverter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Deployments Service Implementation
 */
@Slf4j
@Service
public class DeploymentsServiceImpl extends ServiceImpl<DeploymentsMapper, Deployments> implements DeploymentsService {

    @Autowired
    private AutoDLClient autoDLClient;

    @Autowired
    private ImagesMapper imagesMapper;

    @Override
    public CreateDeploymentData createDeployment(CreateDeploymentReq req) {
        // 从请求中获取uid
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        String uid = (String) request.getAttribute("uid");

        // 判断镜像为空或者不存在
        if(req.getContainerTemplate().getImageUuid() == null || req.getContainerTemplate().getImageUuid().isEmpty()){
            throw new RuntimeException("imageUuid为空");
        }
        Images currentImage = imagesMapper.selectOne(
                new QueryWrapper<Images>()
                        .eq("image_uuid", req.getContainerTemplate().getImageUuid())
        );
        if (currentImage == null) {
            throw new RuntimeException("镜像不存在");
        }

        // 将请求转换为部署实体
        Deployments deployment = DeploymentConverter.convertToDeployment(req, uid);
        // 调用AutoDL客户端创建部署
        CreateDeploymentData response = autoDLClient.createDeployment(req);
        // 将响应更新到部署实体
        deployment = DeploymentConverter.convertToDeployment(response, deployment);
        // 保存到数据库
        save(deployment);
        // 记录日志
        log.info("用户[{}]成功创建部署[{}]", uid, deployment.getDeploymentUuid());
        return response;
    }

    @Override
    public DeploymentListData getDeploymentList(DeploymentListReq req) {
        // 从请求中获取uid
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        String uid = (String) request.getAttribute("uid");

        // 调用AutoDL客户端获取部署列表
        DeploymentListData response = autoDLClient.getDeploymentList(req);

        // 如果获取到数据，将其转换为Deployments实体并保存到数据库
        if (response != null && response.getList() != null) {
            for (DeploymentItem deploymentItem : response.getList()) {
                // 先查询是否已存在该deploymentUuid的记录
                Deployments existingDeployment = getOne(
                        new QueryWrapper<Deployments>()
                                .eq("deployment_uuid", deploymentItem.getUuid())
                );

                if (existingDeployment == null) {
                    // 不存在，则转换并保存
                    Deployments deployments = DeploymentConverter.convertToDeployment(deploymentItem, uid);
                    save(deployments);
                } else {
                    // 已存在，则更新现有记录
                    // 保留原有ID和其他不需要更新的字段
                    DeploymentConverter.updateDeployment(existingDeployment, deploymentItem, uid);
                    updateById(existingDeployment);
                }
            }
            // 记录日志
            log.info("用户[{}]获取部署列表，共[{}]个部署", uid, response.getList().size());
        }

        return response;
    }

    @Override
    public Object deleteDeployment(DeploymentDeleteReq req) {
        // 从请求中获取uid
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        String uid = (String) request.getAttribute("uid");

        // 调用AutoDL客户端删除部署
        Object response = autoDLClient.deleteDeployment(req);

        // 更新本地数据库中的部署状态
        if (req.getDeploymentUuid() != null) {
            Deployments deployment = getOne(
                new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<Deployments>()
                    .eq("deployment_uuid", req.getDeploymentUuid())
                    .eq("uid", uid.toString())
            );

            if (deployment != null) {
                deployment.setStatus(DeploymentStatus.STOPPED);
                updateById(deployment);

                // 记录日志
                log.info("用户[{}]删除部署[{}]", uid, req.getDeploymentUuid());
            }
        }

        return response;
    }

    @Override
    public Object stopDeployment(StopDeploymentReq req) {
        // 从请求中获取uid
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        String uid = (String) request.getAttribute("uid");

        // 调用AutoDL客户端停止部署
        Object response = autoDLClient.stopDeployment(req);

        // 更新本地数据库中的部署状态
        if (req.getDeploymentUuid() != null) {
            Deployments deployment = getOne(
                new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<Deployments>()
                    .eq("deployment_uuid", req.getDeploymentUuid())
                    .eq("uid", uid)
            );

            if (deployment != null) {
                deployment.setStatus(com.autodl_backend.local.pojo.enums.DeploymentStatus.STOPPED);
                deployment.setStoppedAt(java.time.LocalDateTime.now());
                updateById(deployment);

                // 记录日志
                log.info("用户[{}]停止部署[{}]", uid, req.getDeploymentUuid());
            }
        }

        return response;
    }

    @Override
    public Object setReplicas(ReplicaNumReq req) {
        // 从请求中获取uid
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        String uid = (String) request.getAttribute("uid");

        // 调用AutoDL客户端设置副本数
        Object response = autoDLClient.setReplicaNum(req);

        // 更新本地数据库中的部署副本数
        if (req.getDeploymentUuid() != null && req.getReplicaNum() != null) {
            Deployments deployment = getOne(
                new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<Deployments>()
                    .eq("deployment_uuid", req.getDeploymentUuid())
                    .eq("uid", uid)
            );

            if (deployment != null) {
                deployment.setReplicaNum(req.getReplicaNum());
                updateById(deployment);

                // 记录日志
                log.info("用户[{}]设置部署[{}]的副本数为[{}]", uid, req.getDeploymentUuid(), req.getReplicaNum());
            }
        }

        return response;
    }
}
