package com.autodl_backend.service;

import com.autodl_backend.integration.DTO.DeploymentDTO;
import com.autodl_backend.pojo.DeploymentEntity;
import com.autodl_backend.pojo.page.PageRequest;
import com.autodl_backend.pojo.page.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * Deployment Service Interface
 */
public interface DeploymentService extends IService<DeploymentEntity> {

    /**
     * Create deployment with AutoDL integration
     */
    String createDeploymentWithAutoDL(DeploymentDTO dto);

    /**
     * List deployments
     */
    PageResponse<DeploymentDTO> listDeployments(PageRequest pageRequest);
}
