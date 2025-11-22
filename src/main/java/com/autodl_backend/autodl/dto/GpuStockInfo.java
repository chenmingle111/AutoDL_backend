package com.autodl_backend.autodl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO for GPU stock information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GpuStockInfo {
    @JsonProperty("idle_gpu_num")
    private Integer idleGpuNum;

    @JsonProperty("total_gpu_num")
    private Integer totalGpuNum;
}
