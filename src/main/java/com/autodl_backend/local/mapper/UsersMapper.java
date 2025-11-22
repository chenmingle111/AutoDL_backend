package com.autodl_backend.local.mapper;


import com.autodl_backend.local.pojo.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Users表的Mapper接口
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {
}
