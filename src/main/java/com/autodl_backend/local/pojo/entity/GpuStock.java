package com.autodl_backend.local.pojo.entity;

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

    // 请求参数相关字段
    @TableField("region_sign")
    private String regionSign; // 地区标识码

    @TableField("cuda_v_from")
    private Integer cudaVFrom; // CUDA版本范围下限

    @TableField("cuda_v_to")
    private Integer cudaVTo; // CUDA版本范围上限

    @TableField("gpu_name_set")
    private String gpuNameSet; // 筛选的GPU型号列表，逗号分隔

    @TableField("memory_size_from")
    private Integer memorySizeFrom; // 内存大小范围下限(GB)

    @TableField("memory_size_to")
    private Integer memorySizeTo; // 内存大小范围上限(GB)

    @TableField("cpu_num_from")
    private Integer cpuNumFrom; // CPU核心数量范围下限

    @TableField("cpu_num_to")
    private Integer cpuNumTo; // CPU核心数量范围上限

    @TableField("price_from")
    private Integer priceFrom; // 价格范围下限(元*1000)

    @TableField("price_to")
    private Integer priceTo; // 价格范围上限(元*1000)

    // 记录相关字段
    @TableField("query_time")
    private LocalDateTime queryTime; // 查询时间

    @TableField("uid")
    private String uid; // 查询用户ID

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
