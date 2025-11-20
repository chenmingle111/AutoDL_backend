package com.autodl_backend.service.impl;

import com.autodl_backend.DTO.ContainerDTO;
import com.autodl_backend.mapper.ContainersMapper;
import com.autodl_backend.pojo.Containers;
import com.autodl_backend.pojo.page.PageResponse;
import com.autodl_backend.service.ContainersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Containers表的服务层实现类
 */
@Service
public class ContainersServiceImpl extends ServiceImpl<ContainersMapper, Containers> implements ContainersService {
    @Override
    public PageResponse<Containers> listContainer(ContainerDTO containerDTO) {
        return null;
    }
}
