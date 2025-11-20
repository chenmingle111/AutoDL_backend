package com.autodl_backend.service.impl;

import com.autodl_backend.mapper.TemplateGpuNamesMapper;
import com.autodl_backend.pojo.TemplateGpuNames;
import com.autodl_backend.service.TemplateGpuNamesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * TemplateGpuNames表的服务层实现类
 */
@Service
public class TemplateGpuNamesServiceImpl extends ServiceImpl<TemplateGpuNamesMapper, TemplateGpuNames>
        implements TemplateGpuNamesService {
}
