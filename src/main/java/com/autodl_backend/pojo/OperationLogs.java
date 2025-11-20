package com.autodl_backend.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("operation_logs")
public class OperationLogs {

    @TableId(type = IdType.AUTO)
    private Long id; // 大整数主键

    @TableField("uid")
    private Integer uid; // 操作人ID

    @TableField("operation_type")
    private String operationType; // 操作类型

    @TableField("resource_type")
    private String resourceType; // 资源类型

    @TableField("resource_id")
    private String resourceId; // 资源ID

    @TableField("request_params")
    private JsonNode requestParams; // 请求参数（JSON）

    @TableField("response_code")
    private String responseCode; // 响应码

    @TableField("ip_address")
    private String ipAddress; // 操作IP

    @TableField("user_agent")
    private String userAgent; // 客户端信息

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}
