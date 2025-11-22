package com.autodl_backend.local.service.impl;

import com.autodl_backend.local.mapper.ContainerTemplatesMapper;

import com.autodl_backend.local.pojo.entity.ContainerTemplates;
import com.autodl_backend.local.service.ContainerTemplatesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * ContainerTemplates表的服务层实现类
 */
@Service
public class ContainerTemplatesServiceImpl extends ServiceImpl<ContainerTemplatesMapper, ContainerTemplates>
        implements ContainerTemplatesService {
}
