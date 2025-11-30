package com.autodl_backend.local.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("blacklist")
public class Blacklist {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("uid")
    private String uid; // 归属用户ID

    @TableField("machine_uuid")
    private String machineUuid; // 被拉黑主机UUID

    @TableField("deployment_container_uuid")
    private String deploymentContainerUuid; // 触发拉黑的容器UUID

    @TableField("comment")
    private String comment; // 拉黑原因

    @TableField("expire_in_minutes")
    private Integer expireInMinutes; // 过期时间

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;
}
