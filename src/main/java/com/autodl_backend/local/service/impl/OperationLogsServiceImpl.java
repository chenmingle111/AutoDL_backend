package com.autodl_backend.local.service.impl;

import com.autodl_backend.local.mapper.OperationLogsMapper;
import com.autodl_backend.local.pojo.entity.OperationLogs;

import com.autodl_backend.local.service.OperationLogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * OperationLogs表的服务层实现类
 */
@Service
public class OperationLogsServiceImpl extends ServiceImpl<OperationLogsMapper, OperationLogs>
        implements OperationLogsService {
}
