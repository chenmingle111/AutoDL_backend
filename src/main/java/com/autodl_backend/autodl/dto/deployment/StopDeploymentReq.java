package com.autodl_backend.autodl.dto.deployment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StopDeploymentReq {

    @JsonProperty(value = "deployment_uuid",required = true)
    private String deploymentUuid;

    @JsonProperty(value = "operate",required = true)
    private String operate = "stop";
}
