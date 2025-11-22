package com.autodl_backend.integration.DTO;

import lombok.Data;

@Data
public class UpdateReplicaNumDTO {

    private String deploymentUuid;
    private Integer replicaNum;
}
