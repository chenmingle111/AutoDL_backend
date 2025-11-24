
package com.autodl_backend.local.service.impl;

import com.autodl_backend.autodl.dto.machines.GpuStockData;
import com.autodl_backend.local.mapper.MachinesMapper;
import com.autodl_backend.local.pojo.entity.Machines;
import com.autodl_backend.local.service.MachinesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Machines表的服务层实现类
 */
@Service
public class MachinesServiceImpl extends ServiceImpl<MachinesMapper, Machines> implements MachinesService {


}
