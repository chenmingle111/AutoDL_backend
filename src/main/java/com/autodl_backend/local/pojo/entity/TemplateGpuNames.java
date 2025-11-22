package com.autodl_backend.local.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("template_gpu_names")
public class TemplateGpuNames {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("template_id")
    private Integer templateId; // 关联容器模板ID

    @TableField("gpu_name")
    private String gpuName; // GPU型号

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
}
