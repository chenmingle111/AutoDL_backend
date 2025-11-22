package com.autodl_backend.local.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("billing_records")
public class BillingRecords {

    @TableId(type = IdType.AUTO)
    private Long id; // 大整数主键

    @TableField("uid")
    private Integer uid; // 归属用户ID

    @TableField("container_uuid")
    private String containerUuid; // 关联容器UUID

    @TableField("deployment_uuid")
    private String deploymentUuid; // 关联部署UUID

    @TableField("start_time")
    private LocalDateTime startTime; // 计费开始时间

    @TableField("end_time")
    private LocalDateTime endTime; // 计费结束时间

    @TableField("duration_seconds")
    private Integer durationSeconds; // 计费时长（秒）

    @TableField("price_per_hour")
    private Integer pricePerHour; // 每小时价格

    @TableField("total_cost")
    private BigDecimal totalCost; // 总费用

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
