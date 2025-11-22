package com.autodl_backend.autodl.dto;

import lombok.Data;

/**
 * Request DTO for creating AutoDL instance.
 */
@Data
public class AutoDLCreateReq {
    private String region;
    private String gpuType;
    private Integer gpuNum;
    // Add other necessary fields here
}
