package com.autodl_backend.local.controller;

import com.autodl_backend.autodl.dto.deployment.*;
import com.autodl_backend.local.pojo.response.ApiResponse;
import com.autodl_backend.local.service.DeploymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Deployment related operations.
 */
@RestController
@RequestMapping("/api/deployments")
public class DeploymentController {

    @Autowired
    private DeploymentsService deploymentsService;

    /**
     * Create a new deployment.
     *
     * @param req Request DTO for creating a deployment.
     * @return Result containing created deployment data.
     */
    @PostMapping("/create")
    public ApiResponse<CreateDeploymentData> createDeployment(@RequestBody CreateDeploymentReq req) {
        return ApiResponse.success(deploymentsService.createDeployment(req));
    }

    /**
     * Get deployment list.
     *
     * @param req Request DTO for querying deployment list.
     * @return Result containing deployment list data.
     */
    @PostMapping("/list")
    public ApiResponse<DeploymentListData> getDeploymentList(@RequestBody DeploymentListReq req) {
        return ApiResponse.success(deploymentsService.getDeploymentList(req));
    }

    /**
     * 设置副本数量
     */
    @PutMapping("/setReplicas")
    public ApiResponse<Object> setReplicas(@RequestBody ReplicaNumReq req) {
        deploymentsService.setReplicas(req);
        return ApiResponse.success();
    }

    /**
     * 停止部署
     */
    @PutMapping("/stop")
    public ApiResponse<Object> stopDeployment(@RequestBody StopDeploymentReq req) {
        deploymentsService.stopDeployment(req);
        return ApiResponse.success();
    }
    /**
     * 删除部署
     */
    @DeleteMapping("/delete")
    public ApiResponse<Object> deleteDeployment(@RequestBody DeploymentDeleteReq req) {
        deploymentsService.deleteDeployment(req);
        return ApiResponse.success();
    }
}
