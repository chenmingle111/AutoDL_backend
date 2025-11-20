package com.autodl_backend.mapper;

import com.autodl_backend.pojo.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * Users表的Mapper接口
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {
}
