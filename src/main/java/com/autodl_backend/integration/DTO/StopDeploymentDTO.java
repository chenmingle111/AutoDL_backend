package com.autodl_backend.integration.DTO;

import lombok.Data;

@Data
public class StopDeploymentDTO {
    private String deploymentUuid;
    private String operate;
}
