package com.autodl_backend.service;

import com.autodl_backend.DTO.DeploymentDTO;
import com.autodl_backend.pojo.Deployments;
import com.autodl_backend.pojo.page.PageRequest;
import com.autodl_backend.pojo.page.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * Deployments表的服务层接口
 */
public interface DeploymentsService extends IService<Deployments> {

    /**
     *创建部署
     */
    String createDeployment(DeploymentDTO dto);

    /**
     * 获取部署列表
     */
    PageResponse<DeploymentDTO> listDeployments(PageRequest pageRequest);


}
