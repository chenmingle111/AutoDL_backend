package com.autodl_backend.integration.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 容器实例查询请求 DTO
 * 用于接收 Body 中的请求参数，通过 @JsonProperty 固定 JSON 字段名
 */
@Data // Lombok 注解，自动生成 getter/setter/toString 等方法（需引入 Lombok 依赖）
public class ContainerDTO {

    /**
     * 部署UUID（必填）
     */
    @JsonProperty("deployment_uuid") // 固定 JSON 字段名：deployment_uuid（与前端参数一致）
    private String deploymentUuid;

    /**
     * 筛选容器UUID（非必填）
     */
    @JsonProperty("container_uuid")
    private String containerUuid;

    /**
     * 筛选容器创建时间范围-开始（非必填）
     * 备注：建议格式为 yyyy-MM-dd HH:mm:ss（如 "2025-01-01 00:00:00"）
     */
    @JsonProperty("date_from")
    private String dateFrom;

    /**
     * 筛选容器创建时间范围-结束（非必填）
     */
    @JsonProperty("date_to")
    private String dateTo;

    /**
     * 筛选GPU型号（非必填）
     */
    @JsonProperty("gpu_name")
    private String gpuName;

    /**
     * 筛选容器CPU核心数量范围-最小（非必填）
     */
    @JsonProperty("cpu_num_from")
    private Integer cpuNumFrom;

    /**
     * 筛选容器CPU核心数量范围-最大（非必填）
     */
    @JsonProperty("cpu_num_to")
    private Integer cpuNumTo;

    /**
     * 筛选容器内存大小范围-最小（非必填）
     * 备注：单位需与后端约定（如 MB/GB）
     */
    @JsonProperty("memory_size_from")
    private Integer memorySizeFrom;

    /**
     * 筛选容器内存大小范围-最大（非必填）
     */
    @JsonProperty("memory_size_to")
    private Integer memorySizeTo;

    /**
     * 筛选容器基准价范围-最小（非必填）
     */
    @JsonProperty("price_from")
    private Float priceFrom;

    /**
     * 筛选容器基准价范围-最大（非必填）
     */
    @JsonProperty("price_to")
    private Float priceTo;

    /**
     * 是否查询已经释放的实例（非必填）
     */
    @JsonProperty("released")
    private Boolean released;

    /**
     * 页码（必填，缺省值0）
     */
    @JsonProperty("page_index")
    private Integer pageIndex = 0; // 缺省值：0

    /**
     * 每页条数（必填，缺省值10）
     */
    @JsonProperty("page_size")
    private Integer pageSize = 10; // 缺省值：10

    /**
     * 查询的起始偏移量（非必填）
     * 备注：若传入，优先级高于 page_index（需与后端分页逻辑配合）
     */
    @JsonProperty("offset")
    private Integer offset;
}