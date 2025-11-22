package com.autodl_backend.local.service.impl;

import com.autodl_backend.local.mapper.MachinesMapper;

import com.autodl_backend.local.pojo.entity.Machines;
import com.autodl_backend.service.MachinesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Machines表的服务层实现类
 */
@Service
public class MachinesServiceImpl extends ServiceImpl<MachinesMapper, Machines> implements MachinesService {

}
