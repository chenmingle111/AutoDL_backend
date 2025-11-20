package com.autodl_backend.service;

import com.autodl_backend.DTO.ContainerEventDTO;
import com.autodl_backend.pojo.ContainerEvents;
import com.autodl_backend.pojo.page.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * ContainerEvents表的服务层接口
 */
public interface ContainerEventsService extends IService<ContainerEvents> {

    /**
     * 获取容器事件列表
     */
    PageResponse<ContainerEvents> listContainerEvent(ContainerEventDTO containerEventDTO);
}
