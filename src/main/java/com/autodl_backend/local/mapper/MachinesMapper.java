package com.autodl_backend.local.mapper;


import com.autodl_backend.local.pojo.entity.Machines;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Machines表的Mapper接口
 */
@Mapper
public interface MachinesMapper extends BaseMapper<Machines> {
}
