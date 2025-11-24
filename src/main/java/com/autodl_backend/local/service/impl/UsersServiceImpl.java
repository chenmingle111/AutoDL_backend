package com.autodl_backend.local.service.impl;

import com.autodl_backend.autodl.client.AutoDLClient;
import com.autodl_backend.autodl.dto.deployment.BlacklistReq;
import com.autodl_backend.local.exception.UserNotFoundException;
import com.autodl_backend.local.mapper.UsersMapper;
import com.autodl_backend.local.pojo.entity.Users;
import com.autodl_backend.local.service.UsersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Users表的服务层实现类
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Autowired
    private  AutoDLClient autoDLClient;

    @Override
    public Users findByAuthToken(String authToken) {
        if (!StringUtils.hasText(authToken)) {
            return null;
        }

        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("auth_token", authToken)
                   .eq("is_deleted", 0);

        return getOne(queryWrapper);
    }

    @Override
    public Users getUserById(Integer userId) throws UserNotFoundException {
        if (userId == null) {
            throw new UserNotFoundException("用户ID不能为空");
        }

        Users user = getById(userId);
        if (user == null || user.getIsDeleted() == 1) {
            throw new UserNotFoundException(userId);
        }

        return user;
    }

    @Override
    public BlacklistReq getBlacklist() {
        return (BlacklistReq) autoDLClient.setBlacklist();
    }

    @Override
    public void checkUserDisabled(Users user) throws UserDisabledException {
        if (user == null) {
            throw new UserDisabledException("用户对象不能为空");
        }

        if (user.getIsDeleted() == 1) {
            throw new UserDisabledException("用户账户已被管理员禁用");
        }
    }
}
