package com.autodl_backend.local.service.impl;

import com.autodl_backend.autodl.client.AutoDLClient;
import com.autodl_backend.autodl.dto.deployment.*;
import com.autodl_backend.local.mapper.DeploymentsMapper;
import com.autodl_backend.local.pojo.entity.Deployments;
import com.autodl_backend.local.service.DeploymentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Deployments Service Implementation
 */
@Service
public class DeploymentsServiceImpl extends ServiceImpl<DeploymentsMapper, Deployments> implements DeploymentsService {

    @Autowired
    private AutoDLClient autoDLClient;

    @Override
    public CreateDeploymentData createDeployment(CreateDeploymentReq req) {

        //镜像uuid为空
        if(req.getContainerTemplate().getImageUuid() == null || req.getContainerTemplate().getImageUuid().equals("")){
            throw new RuntimeException("imageUuid为空");
        }
        //镜像不存在
//        if(response != null  &&
//                response.getMessage().contains("私有镜像不存在")){
//
//        }
        return autoDLClient.createDeployment(req);
    }

    @Override
    public DeploymentListData getDeploymentList(DeploymentListReq req) {
        return autoDLClient.getDeploymentList(req);
    }

    @Override
    public Object deleteDeployment(DeploymentDeleteReq req) {
        return autoDLClient.deleteDeployment(req);
    }

    @Override
    public Object stopDeployment(StopDeploymentReq req) {
        return autoDLClient.stopDeployment(req);
    }

    @Override
    public Object setReplicas(ReplicaNumReq req) {
        return autoDLClient.setReplicaNum(req);
    }
}
