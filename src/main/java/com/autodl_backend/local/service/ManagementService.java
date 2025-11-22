package com.autodl_backend.local.service;

import com.autodl_backend.autodl.dto.deployment.BlacklistReq;
import com.autodl_backend.autodl.dto.machines.GpuStockData;

/**
 * Service interface for management operations.
 */
public interface ManagementService {

    /**
     * Set scheduling blacklist
     */
    Object setBlacklist(BlacklistReq req);

    /**
     * Get GPU stock information
     */
    GpuStockData getGpuStock();
}
