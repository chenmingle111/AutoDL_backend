package com.autodl_backend.local.pojo.entity;

import com.autodl_backend.local.pojo.enums.DeploymentStatus;
import com.autodl_backend.local.pojo.enums.DeploymentType;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("deployments")
public class Deployments {

    @TableId(type = IdType.AUTO)
    private Integer id; //部署id

    @TableField("uid")
    private Integer uid; // 归属用户ID

    @TableField("deployment_uuid")
    private String deploymentUuid; // 部署唯一标识

    @TableField("name")
    private String name; // 部署名称

    @TableField("deployment_type")
    private DeploymentType deploymentType; // 部署类型

    @TableField("status")
    private DeploymentStatus status; // 部署状态

    @TableField("replica_num")
    private Integer replicaNum; // 副本数量

    @TableField("parallelism_num")
    private Integer parallelismNum; // Job并行容器数

    @TableField("reuse_container")
    private Boolean reuseContainer; // 是否复用容器

    @TableField("starting_num")
    private Integer startingNum; // 启动中容器数

    @TableField("running_num")
    private Integer runningNum; // 运行中容器数

    @TableField("finished_num")
    private Integer finishedNum; // 已完成容器数

    @TableField("image_uuid")
    private String imageUuid; // 关联镜像UUID

    @TableField("price_estimates")
    private BigDecimal priceEstimates; // 预估费用

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @TableField("stopped_at")
    private LocalDateTime stoppedAt; // 停止时间

    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;
}
