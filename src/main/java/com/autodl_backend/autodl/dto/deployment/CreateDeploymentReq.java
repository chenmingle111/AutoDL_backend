package com.autodl_backend.autodl.dto.deployment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDeploymentReq {

    @JsonProperty(value = "name",required = true)
    private String name;

    @JsonProperty(value = "deployment_type",required = true)
    private String deploymentType;

    @JsonProperty(value = "replica_num",required = true)
    private Integer replicaNum;

    @JsonProperty(value = "parallelism_num",required = true)
    private Integer parallelismNum;

    @JsonProperty("reuse_container")
    private Boolean reuseContainer;

    @JsonProperty("reuse_container_scope")
    private String reuseContainerScope;

    @JsonProperty("service_6006_port_protocol")
    private String service6006PortProtocol;

    @JsonProperty("service_6008_port_protocol")
    private String service6008PortProtocol;

    @JsonProperty(value = "container_template",required = true)
    private ContainerTemplate containerTemplate;
}
