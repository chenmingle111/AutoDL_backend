package com.autodl_backend.local.service;

import com.autodl_backend.autodl.dto.deployment.*;
import com.autodl_backend.local.pojo.entity.Deployments;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * Deployments表的服务层接口
 */
@Service
public interface DeploymentsService extends IService<Deployments> {

    CreateDeploymentData createDeployment(CreateDeploymentReq req);

    DeploymentListData getDeploymentList(DeploymentListReq req);

    Object deleteDeployment(DeploymentDeleteReq req);

    Object stopDeployment(StopDeploymentReq req);

    Object setReplicas(ReplicaNumReq req);
}
