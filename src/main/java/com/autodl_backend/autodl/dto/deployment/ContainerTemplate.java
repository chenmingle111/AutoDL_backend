package com.autodl_backend.autodl.dto.deployment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContainerTemplate {


    @JsonProperty(value = "dc_list",required = true)
    private List<String> dcList;

    @JsonProperty(value = "cuda_v_from",required = true)
    private Integer cudaVFrom;

    @JsonProperty(value = "cuda_v_to",required = true)
    private Integer cudaVTo;

    @JsonProperty(value = "gpu_name_set",required = true)
    private List<String> gpuNameSet;

    @JsonProperty(value = "gpu_num",required = true)
    private Integer gpuNum;

    @JsonProperty(value = "memory_size_from",required = true)
    private Integer memorySizeFrom;

    @JsonProperty(value = "memory_size_to",required = true)
    private Integer memorySizeTo;

    @JsonProperty(value = "cpu_num_from",required = true)
    private Integer cpuNumFrom;

    @JsonProperty(value = "cpu_num_to",required = true)
    private Integer cpuNumTo;

    @JsonProperty(value = "price_from",required = true)
    private Integer priceFrom;

    @JsonProperty(value = "price_to",required = true)
    private Integer priceTo;

    @JsonProperty(value = "image_uuid",required = true)
    private String imageUuid;

    @JsonProperty("cmd_before_shutdown")
    private String cmdBeforeShutdown;

    @JsonProperty("cmd")
    private String cmd;
}
