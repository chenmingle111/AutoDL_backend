package com.autodl_backend.local.service.impl;


import com.autodl_backend.local.mapper.BillingRecordsMapper;
import com.autodl_backend.local.pojo.entity.BillingRecords;
import com.autodl_backend.local.service.BillingRecordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * BillingRecords表的服务层实现类
 */
@Service
public class BillingRecordsServiceImpl extends ServiceImpl<BillingRecordsMapper, BillingRecords>
        implements BillingRecordsService {
    @Override
    public boolean save(BillingRecords entity) {
        return super.save(entity);
    }
}
