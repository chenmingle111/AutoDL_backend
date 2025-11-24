package com.autodl_backend.local.service;

import com.autodl_backend.autodl.dto.deployment.BlacklistReq;
import com.autodl_backend.local.exception.UserDisabledException;
import com.autodl_backend.local.exception.UserNotFoundException;
import com.autodl_backend.local.pojo.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * Users表的服务层接口
 */
public interface UsersService extends IService<Users> {

    /**
     * 根据认证令牌查询用户
     * 
     * @param authToken 认证令牌
     * @return 用户对象，如果不存在则返回null
     */
    Users findByAuthToken(String authToken);

    /**
     * 根据用户ID获取用户
     * 
     * @param userId 用户ID
     * @return 用户对象
     * @throws UserNotFoundException 如果用户不存在
     */
    Users getUserById(Integer userId) throws UserNotFoundException;

    /**
     * 检查用户是否被禁用
     * 
     * @param user 用户对象
     * @throws UserDisabledException 如果用户被禁用
     */
    void checkUserDisabled(Users user) throws UserDisabledException;

/**
 * 获取黑名单列表的方法
 *
 * @return 返回一个BlacklistReq对象，其中包含黑名单相关信息
 */
    BlacklistReq getBlacklist();
}
