package com.autodl_backend.service.impl;

import com.autodl_backend.mapper.OperationLogsMapper;
import com.autodl_backend.pojo.OperationLogs;
import com.autodl_backend.service.OperationLogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * OperationLogs表的服务层实现类
 */
@Service
public class OperationLogsServiceImpl extends ServiceImpl<OperationLogsMapper, OperationLogs>
        implements OperationLogsService {
}
