package com.autodl_backend.autodl.dto.machines;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Map;

/**
 * GPU库存查询请求DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GpuStockReq {

    /**
     * 不同地区的标识码（见附录）
     */
    @JsonProperty("region_sign")
    private String regionSign;

    /**
     * 筛选机器GPU驱动可支持的CUDA版本范围下限
     * 约定：整数112代表11.2的CUDA版本（详细规则见附录）
     */
    @JsonProperty("cuda_v_from")
    private Integer cudaVFrom;

    /**
     * 筛选机器GPU驱动可支持的CUDA版本范围上限
     * 约定：整数112代表11.2的CUDA版本（详细规则见附录）
     */
    @JsonProperty("cuda_v_to")
    private Integer cudaVTo;

    /**
     * 筛选可调度的GPU型号列表
     * 参考网页创建弹性部署时显示的GPU型号名称
     */
    @JsonProperty("gpu_name_set")
    private List<String> gpuNameSet;

    /**
     * 筛选可调度的容器内存大小范围下限
     * 单位：GB
     */
    @JsonProperty("memory_size_from")
    private Integer memorySizeFrom;

    /**
     * 筛选可调度的容器内存大小范围上限
     * 单位：GB
     */
    @JsonProperty("memory_size_to")
    private Integer memorySizeTo;

    /**
     * 筛选可调度的CPU核心数量范围下限
     * 单位：1vCPU
     */
    @JsonProperty("cpu_num_from")
    private Integer cpuNumFrom;

    /**
     * 筛选可调度的CPU核心数量范围上限
     * 单位：1vCPU
     */
    @JsonProperty("cpu_num_to")
    private Integer cpuNumTo;

    /**
     * 筛选可调度的价格范围下限
     * 单位：元 * 1000，如0.1元填写100
     */
    @JsonProperty("price_from")
    private Integer priceFrom;

    /**
     * 筛选可调度的价格范围上限
     * 单位：元 * 1000，如0.1元填写100
     */
    @JsonProperty("price_to")
    private Integer priceTo;
}
