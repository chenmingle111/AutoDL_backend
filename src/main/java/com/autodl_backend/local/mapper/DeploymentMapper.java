package com.autodl_backend.local.mapper;


import com.autodl_backend.local.pojo.entity.DeploymentEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Deployments表的Mapper接口
 */
@Mapper
public interface DeploymentMapper extends BaseMapper<DeploymentEntity> {
}
