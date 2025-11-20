package com.autodl_backend.DTO;

import lombok.Data;

@Data
public class UpdateReplicaNumDTO {

    private String deploymentUuid;
    private Integer replicaNum;
}
