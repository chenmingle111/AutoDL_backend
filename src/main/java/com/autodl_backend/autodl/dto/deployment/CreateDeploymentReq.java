package com.autodl_backend.autodl.dto.deployment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDeploymentReq {

    @JsonProperty("name")
    private String name;

    @JsonProperty("deployment_type")
    private String deploymentType;

    @JsonProperty("replica_num")
    private Integer replicaNum;

    @JsonProperty("parallelism_num")
    private Integer parallelismNum;

    @JsonProperty("reuse_container")
    private Boolean reuseContainer;

    @JsonProperty("reuse_container_scope")
    private String reuseContainerScope;

    @JsonProperty("service_6006_port_protocol")
    private String service6006PortProtocol;

    @JsonProperty("service_6008_port_protocol")
    private String service6008PortProtocol;

    @JsonProperty("container_template")
    private ContainerTemplate containerTemplate;
}
