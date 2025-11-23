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

    @JsonProperty("cuda_v")
    private Integer cudaV;

    @JsonProperty("gpu_name_set")
    private List<String> gpuNameSet;

    @JsonProperty("gpu_num")
    private Integer gpuNum;

    @JsonProperty("memory_size_from")
    private Integer memorySizeFrom;

    @JsonProperty("memory_size_to")
    private Integer memorySizeTo;

    @JsonProperty("cpu_num_from")
    private Integer cpuNumFrom;

    @JsonProperty("cpu_num_to")
    private Integer cpuNumTo;

    @JsonProperty("price_from")
    private Integer priceFrom;

    @JsonProperty("price_to")
    private Integer priceTo;

    @JsonProperty("image_uuid")
    private String imageUuid;

    @JsonProperty("cmd")
    private String cmd;
}
