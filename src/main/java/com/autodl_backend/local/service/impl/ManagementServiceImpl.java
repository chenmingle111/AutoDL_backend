package com.autodl_backend.local.service.impl;

import com.autodl_backend.autodl.client.AutoDLClient;
import com.autodl_backend.autodl.dto.deployment.BlacklistReq;
import com.autodl_backend.autodl.dto.machines.GpuStockData;
import com.autodl_backend.autodl.dto.machines.GpuStockReq;
import com.autodl_backend.local.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of ManagementService.
 */
@Service
public class ManagementServiceImpl implements ManagementService {

    @Autowired
    private AutoDLClient autoDLClient;

    @Override
    public Object setBlacklist(BlacklistReq req) {
        return autoDLClient.setBlacklist(req);
    }

    @Override
    public GpuStockData getGpuStock(GpuStockReq req) {
        return autoDLClient.getGpuStock(req);
    }
}
