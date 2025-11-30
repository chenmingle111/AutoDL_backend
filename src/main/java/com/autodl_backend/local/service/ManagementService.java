package com.autodl_backend.local.service;

import com.autodl_backend.autodl.dto.machines.SetBlacklistReq;
import com.autodl_backend.autodl.dto.machines.GpuStockData;
import com.autodl_backend.autodl.dto.machines.GpuStockReq;

/**
 * Service interface for management operations.
 */
public interface ManagementService {

    /**
     * Set scheduling blacklist
     */
    Object setBlacklist(SetBlacklistReq req);

    /**
     * Get GPU stock information
     */
    GpuStockData getGpuStock(GpuStockReq req);
}
