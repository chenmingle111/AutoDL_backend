package com.autodl_backend.pojo;

import com.autodl_backend.pojo.enums.ContainerStatus;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("containers")
public class Containers {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @JsonProperty("uuid")
    @TableField("container_uuid")
    private String containerUuid; // 容器唯一标识

    @JsonProperty("deployment_uuid")
    @TableField("deployment_uuid")
    private String deploymentUuid; // 关联部署UUID

    @JsonProperty("machine_id")
    @TableField("machine_uuid")
    private String machineUuid; // 关联主机UUID

    @TableField("status")
    private ContainerStatus status; // 容器状态

    @TableField("gpu_name")
    private String gpuName; // 分配的GPU型号

    @TableField("gpu_num")
    private Integer gpuNum; // 分配的GPU数量

    @TableField("cpu_num")
    private Integer cpuNum; // 分配的CPU核心数

    @TableField("memory_size")
    private Long memorySize; // 分配的内存（字节）

    @TableField("image_uuid")
    private String imageUuid; // 关联镜像UUID

    @TableField("price")
    private Integer price; // 基准价格

    @TableField("ssh_command")
    private String sshCommand; // SSH登录指令

    @JsonProperty("root_password")
    @TableField("ssh_password")
    private String sshPassword; // SSH密码

    @TableField("service_url")
    private String serviceUrl; // 自定义服务地址

    @TableField("proxy_host")
    private String proxyHost; // 自定义服务HOST地址

    @TableField("custom_port")
    private Integer customPort; // 自定义服务端口号

    @TableField("started_at")
    private LocalDateTime startedAt; // 启动时间

    @TableField("stopped_at")
    private LocalDateTime stoppedAt; // 停止时间

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @TableField("released")
    private Boolean released; // 是否已释放

    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;
}
