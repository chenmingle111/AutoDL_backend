package com.autodl_backend.service.impl;

import com.autodl_backend.mapper.GpuStockMapper;
import com.autodl_backend.pojo.GpuStock;
import com.autodl_backend.service.GpuStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * GpuStock表的服务层实现类
 */
@Service
public class GpuStockServiceImpl extends ServiceImpl<GpuStockMapper, GpuStock> implements GpuStockService {
}
