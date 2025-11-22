package com.autodl_backend.integration.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerEventDTO {

    @JsonProperty("deployment_uuid")
    private String deploymentUuid;

    @JsonProperty("deployment_container_uuid")
    private String deploymentContainerUuid;

    @JsonProperty("page_index")
    private Integer pageIndex;

    @JsonProperty("page_size")
    private Integer pageSize;

    @JsonProperty("offset")
    private Integer offset;
}
