package com.autodl_backend.local.service.impl;

import com.autodl_backend.local.mapper.UsersMapper;
import com.autodl_backend.local.pojo.entity.Users;
import com.autodl_backend.local.service.UsersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Users表的服务层实现类
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
