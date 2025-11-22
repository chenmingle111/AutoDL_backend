package com.autodl_backend.autodl.dto;

import lombok.Data;

/**
 * Response DTO for AutoDL API.
 */
@Data
public class AutoDLResp {
    private Integer code;
    private String msg;
    private Object data;
    // Add other necessary fields here
}
