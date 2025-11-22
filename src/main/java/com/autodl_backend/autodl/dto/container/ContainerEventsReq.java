package com.autodl_backend.autodl.dto.container;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Request DTO for querying container events.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerEventsReq {
    @JsonProperty("deployment_uuid")
    private String deploymentUuid;

    @JsonProperty("deployment_container_uuid")
    private String deploymentContainerUuid;

    @JsonProperty("page_index")
    private Integer pageIndex = 1;

    @JsonProperty("page_size")
    private Integer pageSize = 10;

    @JsonProperty("offset")
    private Integer offset = 0;
}
