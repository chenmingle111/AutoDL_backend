package com.autodl_backend.local.service.impl;


import com.autodl_backend.local.mapper.ContainersMapper;
import com.autodl_backend.local.pojo.entity.Containers;
import com.autodl_backend.local.service.ContainersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Containers表的服务层实现类
 */
@Service
public class ContainersServiceImpl extends ServiceImpl<ContainersMapper, Containers> implements ContainersService {

}
