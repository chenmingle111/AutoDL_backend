package com.autodl_backend.integration.DTO;

import lombok.Data;

/**
 * Response DTO for AutoDL API.
 * This is a placeholder structure. Add actual fields as needed.
 */
@Data
public class AutoDLResp<T> {
    private String code;
    private String msg;
    private T data;
    // Add other necessary fields here
}
