package com.autodl_backend.pojo;

import com.autodl_backend.pojo.enums.UserRole;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("users") // 指定数据库表名
public class Users {

    @TableId(type = IdType.AUTO) // 主键自增
    private Integer id;

    @TableField("uid")
    private Integer uid; // 用户唯一标识

    @TableField("user_name")
    private String userName; // 用户名

    @TableField("email")
    private String email; // 用户邮箱

    @TableField("auth_token")
    private String authToken; // 开发者Token

    @TableField("role")
    private UserRole role; // 用户角色

    @TableField("password")
    private String password; // 加密密码

    @TableField(value = "created_at", fill = FieldFill.INSERT) // 插入时自动填充
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE) // 插入/更新时自动填充
    private LocalDateTime updatedAt;

    @TableLogic // 逻辑删除标记（0-未删，1-已删）
    @TableField("is_deleted")
    private Integer isDeleted;
}
