package com.autodl_backend.autodl.task;

import com.autodl_backend.autodl.client.AutoDLClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduled task for syncing state between local DB and AutoDL.
 */
@Slf4j
@Component
public class AutoDLStateSyncTask {

    @Autowired
    private AutoDLClient autoDLClient;

    // @Autowired
    // private DeploymentService deploymentService;

    /**
     * Sync state every minute.
     */
    @Scheduled(fixedRate = 60000)
    public void syncState() {
        log.info("Starting state sync with AutoDL...");

        // Example logic:
        // 1. Get active deployments from local DB
        // List<DeploymentEntity> deployments =
        // deploymentService.findActiveDeployments();

        // 2. For each deployment, check status on AutoDL
        // for (DeploymentEntity dep : deployments) {
        // AutoDLResp resp = autoDLClient.getInstanceStatus(dep.getInstanceId());
        // // Update local status based on resp
        // }

        log.info("State sync completed.");
    }
}
