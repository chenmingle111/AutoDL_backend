package com.autodl_backend.local.service.impl;

import com.autodl_backend.local.mapper.SchedulingBlacklistMapper;

import com.autodl_backend.local.pojo.entity.SchedulingBlacklist;
import com.autodl_backend.local.service.SchedulingBlacklistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * SchedulingBlacklist表的服务层实现类
 */
@Service
public class SchedulingBlacklistServiceImpl extends ServiceImpl<SchedulingBlacklistMapper, SchedulingBlacklist>
        implements SchedulingBlacklistService {
}
