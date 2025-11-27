
package com.autodl_backend.autodl.dto.deployment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 删除部署请求参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeploymentDeleteReq {
    /**
     * 部署uuid
     */
    @JsonProperty("deployment_uuid")
    private String deploymentUuid;

}
