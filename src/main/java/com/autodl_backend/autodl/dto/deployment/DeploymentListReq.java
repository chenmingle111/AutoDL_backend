package com.autodl_backend.autodl.dto.deployment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 部署列表查询请求参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeploymentListReq {

    /**
     * 页码
     */
    @JsonProperty(value = "page_index",required = true)
    private Integer pageIndex;

    /**
     * 每页条目数
     */
    @JsonProperty(value = "page_size",required = true)
    private Integer pageSize;

    /**
     * 部署名称
     */
    @JsonProperty("name")
    private String name;

    /**
     * 部署状态筛选
     */
    @JsonProperty("status")
    private String status;

    /**
     * 部署UUID
     */
    @JsonProperty("deployment_uuid")
    private String deploymentUuid;
}
