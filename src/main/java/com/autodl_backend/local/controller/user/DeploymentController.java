package com.autodl_backend.local.controller.user;

import com.autodl_backend.autodl.dto.deployment.CreateDeploymentData;
import com.autodl_backend.autodl.dto.deployment.CreateDeploymentReq;
import com.autodl_backend.autodl.dto.deployment.DeploymentListData;
import com.autodl_backend.autodl.dto.deployment.DeploymentListReq;
import com.autodl_backend.local.pojo.response.ApiResponse;
import com.autodl_backend.local.service.DeploymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Deployment related operations.
 */
@RestController
@RequestMapping("/api/user/deployments")
public class DeploymentController {

    @Autowired
    private DeploymentsService deploymentsService;

    /**
     * Create a new deployment.
     *
     * @param req Request DTO for creating a deployment.
     * @return Result containing created deployment data.
     */
    @PostMapping
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
}
