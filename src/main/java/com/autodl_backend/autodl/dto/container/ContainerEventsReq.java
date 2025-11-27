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
    @JsonProperty(value = "deployment_uuid",required = true)
    private String deploymentUuid;

    @JsonProperty("deployment_container_uuid")
    private String deploymentContainerUuid;

    @JsonProperty(value = "page_index",required = true)
    private Integer pageIndex = 1;

    @JsonProperty(value = "page_size",required = true)
    private Integer pageSize = 10;

    @JsonProperty("offset")
    private Integer offset = 0;
}
