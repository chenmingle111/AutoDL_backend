package com.autodl_backend.local.service.impl;

import com.autodl_backend.local.mapper.ContainerEventsMapper;
import com.autodl_backend.local.pojo.entity.ContainerEvents;
import com.autodl_backend.local.service.ContainerEventsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * ContainerEvents表的服务层实现类
 */
@Service
public class ContainerEventsServiceImpl extends ServiceImpl<ContainerEventsMapper, ContainerEvents>
        implements ContainerEventsService {
}
