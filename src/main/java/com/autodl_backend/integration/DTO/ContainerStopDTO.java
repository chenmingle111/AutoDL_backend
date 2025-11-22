package com.autodl_backend.integration.DTO;

import lombok.Data;

@Data
public class ContainerStopDTO {
    /**
     * 部署的容器uuid
     */
    private String deploymentContainerUuid;
    /**
     * 对于ReplicaSet类型的部署，是否同时将replica num副本数减少1个
     */
    private Boolean decreaseOneReplicaNum;
}
