package com.autodl_backend.autodl.dto.container;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Request DTO for stopping a container.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerStopReq {
    @JsonProperty("deployment_container_uuid")
    private String deploymentContainerUuid;

    @JsonProperty("decrease_one_replica_num")
    private Boolean decreaseOneReplicaNum = false;
}
