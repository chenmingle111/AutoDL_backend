package com.autodl_backend.service.impl;

import com.autodl_backend.mapper.MachinesMapper;
import com.autodl_backend.pojo.Machines;
import com.autodl_backend.service.MachinesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Machines表的服务层实现类
 */
@Service
public class MachinesServiceImpl extends ServiceImpl<MachinesMapper, Machines> implements MachinesService {
}
