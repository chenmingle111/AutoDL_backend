package com.autodl_backend.local.service.impl;

import com.autodl_backend.autodl.client.AutoDLClient;
import com.autodl_backend.autodl.dto.deployment.CreateDeploymentData;
import com.autodl_backend.autodl.dto.deployment.CreateDeploymentReq;
import com.autodl_backend.autodl.dto.deployment.DeploymentListData;
import com.autodl_backend.autodl.dto.deployment.DeploymentListReq;
import com.autodl_backend.local.mapper.DeploymentsMapper;
import com.autodl_backend.local.pojo.entity.Deployments;
import com.autodl_backend.local.service.DeploymentsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Deployments Service Implementation
 */
@Service
public class DeploymentsServiceImpl extends ServiceImpl<DeploymentsMapper, Deployments> implements DeploymentsService {

    @Autowired
    private AutoDLClient autoDLClient;

    @Override
    public CreateDeploymentData createDeployment(CreateDeploymentReq req) {
        return autoDLClient.createDeployment(req);
    }

    @Override
    public DeploymentListData getDeploymentList(DeploymentListReq req) {
        return autoDLClient.getDeploymentList(req);
    }

}
