package com.autodl_backend.autodl.dto.deployment;

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
    @JsonProperty(value = "deployment_container_uuid",required = true)
    private String deploymentContainerUuid;

    @JsonProperty("expire_in_minutes")
    private Integer expireInMinutes=1440;

    private String comment;
}
