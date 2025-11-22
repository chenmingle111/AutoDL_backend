package com.autodl_backend.autodl.dto.container;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Item DTO for container event.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerEventItem {
    @JsonProperty("deployment_container_uuid")
    private String deploymentContainerUuid;

    private String status;

    @JsonProperty("created_at")
    private String createdAt;
}
