package com.autodl_backend.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("gpu_stock")
public class GpuStock {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("gpu_name")
    private String gpuName; // GPU型号

    @TableField("idle_gpu_num")
    private Integer idleGpuNum; // 空闲GPU数量

    @TableField("total_gpu_num")
    private Integer totalGpuNum; // 总GPU数量

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
