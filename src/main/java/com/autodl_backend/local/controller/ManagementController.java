package com.autodl_backend.local.controller;

import com.autodl_backend.autodl.dto.deployment.BlacklistReq;
import com.autodl_backend.autodl.dto.machines.GpuStockData;
import com.autodl_backend.local.pojo.response.ApiResponse;
import com.autodl_backend.local.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Management related operations.
 */
@RestController
@RequestMapping("/api/management")
public class ManagementController {

    @Autowired
    private ManagementService managementService;

    /**
     * Set scheduling blacklist.
     *
     * @param req Request DTO for setting blacklist.
     * @return Result indicating success.
     */
    @PostMapping("/blacklist")
    public ApiResponse<Object> setBlacklist(@RequestBody BlacklistReq req) {
        return ApiResponse.success(managementService.setBlacklist(req));
    }

    /**
     * Get GPU stock information.
     *
     * @return Result containing GPU stock data.
     */
    @GetMapping("/gpu-stock")
    public ApiResponse<GpuStockData> getGpuStock() {
        return ApiResponse.success(managementService.getGpuStock());
    }
}
