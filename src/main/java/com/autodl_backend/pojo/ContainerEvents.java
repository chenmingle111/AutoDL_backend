package com.autodl_backend.pojo;

import com.autodl_backend.pojo.enums.ContainerEventStatus;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("container_events")
public class ContainerEvents {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("deployment_container_uuid")
    private String deploymentContainerUuid; // 容器UUID

    @TableField("container_id")
    private Integer containerId; // 关联容器ID

    @TableField("status")
    private ContainerEventStatus status; // 事件状态

    @TableField("message")
    private String message; // 事件描述

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}
