package com.autodl_backend.service;

import com.autodl_backend.integration.DTO.ContainerDTO;
import com.autodl_backend.integration.DTO.ContainerStopDTO;
import com.autodl_backend.pojo.Containers;
import com.autodl_backend.pojo.page.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * Containers表的服务层接口
 */
public interface ContainersService extends IService<Containers> {

    /**
     * 获取容器细节
     */
    PageResponse<Containers> listContainer(ContainerDTO containerDTO);

    /**
     * 停止容器
     */
    void stopContainer(ContainerStopDTO containerStopDTO);
}
