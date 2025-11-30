package com.autodl_backend.util;

import com.autodl_backend.local.mapper.UsersMapper;
import com.autodl_backend.local.pojo.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Token工具类，用于从token获取用户uid
 */
@Component
public class TokenUtil {

    @Autowired
    private UsersMapper usersMapper;

    /**
     * 从token获取用户uid
     * @param token 用户token
     * @return 用户uid，如果token无效则返回null
     */
    public String getUidFromToken(String token) {
//        // 测试环境特殊处理，允许token "10000001"通过验证
//        if ("10000001".equals(token)) {
//            return "10000001";
//        }

        if (!TokenManager.validateToken(token)) {
            return null;
        }

        // 通过token查询用户
        Users user = usersMapper.selectOne(
            new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<Users>()
                .eq("auth_token", token)
        );

        return user != null ? user.getUid() : null;
    }

    /**
     * 验证token是否有效
     * @param token 用户token
     * @return token是否有效
     */
    public boolean isTokenValid(String token) {
        return getUidFromToken(token) != null;
    }
}
