package com.autodl_backend.mapper;

import com.autodl_backend.pojo.Deployments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Deployments表的Mapper接口
 */
@Mapper
public interface DeploymentsMapper extends BaseMapper<Deployments> {
}
