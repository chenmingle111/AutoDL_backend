package com.autodl_backend.service.impl;

import com.autodl_backend.DTO.DeploymentDTO;
import com.autodl_backend.mapper.DeploymentsMapper;
import com.autodl_backend.pojo.Deployments;
import com.autodl_backend.pojo.page.PageRequest;
import com.autodl_backend.pojo.page.PageResponse;
import com.autodl_backend.service.DeploymentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Deployments表的服务层实现类
 */
@Service
public class DeploymentsServiceImpl extends ServiceImpl<DeploymentsMapper, Deployments> implements DeploymentsService {
    @Override
    public String createDeployment(DeploymentDTO dto) {
        return "";
    }

    @Override
    public PageResponse<DeploymentDTO> listDeployments(PageRequest pageRequest) {
        return null;
    }
}
