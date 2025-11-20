package com.autodl_backend.pojo;

import com.autodl_backend.util.ListToStringTypeHandler;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName("container_templates")
public class ContainerTemplates {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("deployment_id")
    private Integer deploymentId; // 关联部署ID

    @TableField("cuda_v")
    private Integer cudaV; // CUDA版本

    @TableField(value = "gpu_name_set", typeHandler = ListToStringTypeHandler.class)
    private List<String> gpuNameSet; // GPU型号列表

    @TableField("gpu_num")
    private Integer gpuNum; // GPU数量

    @TableField("cpu_num_from")
    private Integer cpuNumFrom; // CPU核心下限

    @TableField("cpu_num_to")
    private Integer cpuNumTo; // CPU核心上限

    @TableField("memory_size_from")
    private Long memorySizeFrom; // 内存下限（字节）

    @TableField("memory_size_to")
    private Long memorySizeTo; // 内存上限（字节）

    @TableField("price_from")
    private Integer priceFrom; // 价格下限

    @TableField("price_to")
    private Integer priceTo; // 价格上限

    @TableField("cmd")
    private String cmd; // 容器启动命令

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
