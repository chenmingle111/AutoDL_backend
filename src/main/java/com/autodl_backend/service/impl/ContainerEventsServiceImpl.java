package com.autodl_backend.service.impl;

import com.autodl_backend.mapper.ContainerEventsMapper;
import com.autodl_backend.pojo.ContainerEvents;
import com.autodl_backend.service.ContainerEventsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * ContainerEvents表的服务层实现类
 */
@Service
public class ContainerEventsServiceImpl extends ServiceImpl<ContainerEventsMapper, ContainerEvents>
        implements ContainerEventsService {
}
