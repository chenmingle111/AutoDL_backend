package com.autodl_backend.local.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("scheduling_blacklist")
public class SchedulingBlacklist {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("uid")
    private Integer uid; // 归属用户ID

    @TableField("machine_uuid")
    private String machineUuid; // 被拉黑主机UUID

    @TableField("deployment_container_uuid")
    private String deploymentContainerUuid; // 触发拉黑的容器UUID

    @TableField("comment")
    private String comment; // 拉黑原因

    @TableField("expires_at")
    private LocalDateTime expiresAt; // 过期时间

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;
}
