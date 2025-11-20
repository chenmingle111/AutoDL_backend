package com.autodl_backend.service.impl;

import com.autodl_backend.mapper.BillingRecordsMapper;
import com.autodl_backend.pojo.BillingRecords;
import com.autodl_backend.service.BillingRecordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * BillingRecords表的服务层实现类
 */
@Service
public class BillingRecordsServiceImpl extends ServiceImpl<BillingRecordsMapper, BillingRecords>
        implements BillingRecordsService {
}
