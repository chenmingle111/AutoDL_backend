package com.autodl_backend.service.impl;

import com.autodl_backend.mapper.UsersMapper;
import com.autodl_backend.pojo.Users;
import com.autodl_backend.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Users表的服务层实现类
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
}
