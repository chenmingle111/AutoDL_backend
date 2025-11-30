package com.autodl_backend.autodl.dto.machines;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Item DTO for blacklist response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class geteBlacklistItem {
    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("data_center")
    private String dataCenter;

    @JsonProperty("expired_time")
    private String expiredTime;

    @JsonProperty("machine_id")
    private String machineId;

    private String msg;

    @JsonProperty("updated_at")
    private String updatedAt;
}
