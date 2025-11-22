package com.autodl_backend.autodl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Request DTO for setting a scheduling blacklist.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlacklistReq {
    @JsonProperty("deployment_container_uuid")
    private String deploymentContainerUuid;

    private String comment;
}
