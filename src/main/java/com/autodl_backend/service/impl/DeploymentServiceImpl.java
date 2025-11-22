package com.autodl_backend.service.impl;

import com.autodl_backend.autodl.client.AutoDLClient;
import com.autodl_backend.autodl.dto.AutoDLCreateReq;
import com.autodl_backend.autodl.dto.AutoDLResp;
import com.autodl_backend.integration.DTO.DeploymentDTO;
import com.autodl_backend.mapper.DeploymentMapper;
import com.autodl_backend.pojo.DeploymentEntity;
import com.autodl_backend.pojo.page.PageRequest;
import com.autodl_backend.pojo.page.PageResponse;
import com.autodl_backend.service.DeploymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Deployment Service Implementation
 */
@Service
public class DeploymentServiceImpl extends ServiceImpl<DeploymentMapper, DeploymentEntity>
        implements DeploymentService {

    @Autowired
    private AutoDLClient autoDLClient;

    @Override
    public String createDeploymentWithAutoDL(DeploymentDTO dto) {
        // 1. Save to local DB (MyBatis-Plus)
        // DeploymentEntity entity = convert(dto);
        // this.save(entity);

        // 2. Call AutoDL API
        AutoDLCreateReq req = new AutoDLCreateReq();
        // req.setRegion(dto.getRegion());
        // ... map other fields

        // AutoDLResp resp = autoDLClient.createInstance(req);

        // 3. Update local DB with external ID
        // if (resp.getCode() == 200) {
        // // update local record
        // }

        return "Deployment created with AutoDL integration";
    }

    @Override
    public PageResponse<DeploymentDTO> listDeployments(PageRequest pageRequest) {
        return null;
    }
}
