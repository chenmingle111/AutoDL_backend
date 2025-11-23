package com.autodl_backend.autodl.dto.deployment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeploymentTemplate {

    @JsonProperty("gpu_name_set")
    private List<String> gpuNameSet;

    @JsonProperty("gpu_num")
    private Integer gpuNum;

    @JsonProperty("image_uuid")
    private String imageUuid;

    @JsonProperty("image_name")
    private String imageName;

    @JsonProperty("cmd")
    private String cmd;

    @JsonProperty("memory_size_from")
    private Long memorySizeFrom;

    @JsonProperty("memory_size_to")
    private Long memorySizeTo;

    @JsonProperty("cpu_num_from")
    private Integer cpuNumFrom;

    @JsonProperty("cpu_num_to")
    private Integer cpuNumTo;

    @JsonProperty("price_from")
    private Integer priceFrom;

    @JsonProperty("price_to")
    private Integer priceTo;

    @JsonProperty("cuda_v")
    private Integer cudaV;
}
