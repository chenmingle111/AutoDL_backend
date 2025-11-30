package com.autodl_backend.autodl.dto.container;

import com.autodl_backend.local.pojo.enums.ContainerStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Item DTO for container list.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerListItem {
    private Integer id;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("data_center")
    private String dataCenter;

    @JsonProperty("machine_id")
    private String machineId;

    @JsonProperty("deployment_uuid")
    private String deploymentUuid;

    private ContainerStatus status;

    @JsonProperty("gpu_name")
    private String gpuName;

    @JsonProperty("gpu_num")
    private Integer gpuNum;

    @JsonProperty("cpu_num")
    private Integer cpuNum;

    @JsonProperty("memory_size")
    private Long memorySize;

    @JsonProperty("image_uuid")
    private String imageUuid;

    private Float price;

    private ContainerInfo info;

    @JsonProperty("started_at")
    private String startedAt;

    @JsonProperty("stopped_at")
    private String stoppedAt;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;
}
