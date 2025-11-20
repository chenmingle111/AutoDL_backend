package com.autodl_backend.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("machines")
public class Machines {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("machine_uuid")
    private String machineUuid; // 主机唯一标识

    @TableField("region")
    private String region; // 主机区域

    @TableField("host_address")
    private String hostAddress; // 主机地址

    @TableField("status")
    private String status; // 主机状态

    @TableField("gpu_name")
    private String gpuName; // GPU型号

    @TableField("total_gpu_num")
    private Integer totalGpuNum; // 总GPU数量

    @TableField("idle_gpu_num")
    private Integer idleGpuNum; // 空闲GPU数量

    @TableField("total_cpu_num")
    private Integer totalCpuNum; // 总CPU核心数

    @TableField("total_memory")
    private Long totalMemory; // 总内存（字节）

    @TableField("cuda_versions")
    private JsonNode cudaVersions; // 支持的CUDA版本（JSON）

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;
}
