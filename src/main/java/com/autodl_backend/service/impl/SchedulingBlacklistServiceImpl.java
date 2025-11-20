package com.autodl_backend.service.impl;

import com.autodl_backend.mapper.SchedulingBlacklistMapper;
import com.autodl_backend.pojo.SchedulingBlacklist;
import com.autodl_backend.service.SchedulingBlacklistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * SchedulingBlacklist表的服务层实现类
 */
@Service
public class SchedulingBlacklistServiceImpl extends ServiceImpl<SchedulingBlacklistMapper, SchedulingBlacklist>
        implements SchedulingBlacklistService {
}
