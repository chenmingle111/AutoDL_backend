package com.autodl_backend.autodl.dto.container;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Request DTO for querying container list.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerListReq {
    @JsonProperty("deployment_uuid")
    private String deploymentUuid;

    @JsonProperty("container_uuid")
    private String containerUuid;

    @JsonProperty("date_from")
    private String dateFrom;

    @JsonProperty("date_to")
    private String dateTo;

    @JsonProperty("gpu_name")
    private String gpuName;

    @JsonProperty("cpu_num_from")
    private Integer cpuNumFrom;

    @JsonProperty("cpu_num_to")
    private Integer cpuNumTo;

    @JsonProperty("memory_size_from")
    private Integer memorySizeFrom;

    @JsonProperty("memory_size_to")
    private Integer memorySizeTo;

    @JsonProperty("price_from")
    private Float priceFrom;

    @JsonProperty("price_to")
    private Float priceTo;

    @JsonProperty("released")
    private Boolean released;

    @JsonProperty("page_index")
    private Integer pageIndex = 1;

    @JsonProperty("page_size")
    private Integer pageSize = 10;

    @JsonProperty("offset")
    private Integer offset = 0;
}
