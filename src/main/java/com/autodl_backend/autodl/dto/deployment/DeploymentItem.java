package com.autodl_backend.autodl.dto.deployment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeploymentItem {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("uid")
    private String uid;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("deployment_type")
    private String deploymentType;

    @JsonProperty("status")
    private String status;

    @JsonProperty("replica_num")
    private Integer replicaNum;

    @JsonProperty("parallelism_num")
    private Integer parallelismNum;

    @JsonProperty("reuse_container")
    private Boolean reuseContainer;

    @JsonProperty("service_port_protocol")
    private String servicePortProtocol;

    @JsonProperty("starting_num")
    private Integer startingNum;

    @JsonProperty("running_num")
    private Integer runningNum;

    @JsonProperty("finished_num")
    private Integer finishedNum;

    @JsonProperty("image_uuid")
    private String imageUuid;

    @JsonProperty("template")
    private DeploymentTemplate template;

    @JsonProperty("price_estimates")
    private BigDecimal priceEstimates;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("stopped_at")
    private String stoppedAt;
}
