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
    @JsonProperty("page_index")
    private Integer pageIndex;

    /**
     * 每页条目数
     */
    @JsonProperty("page_size")
    private Integer pageSize;

    /**
     * 部署名称（不支持模糊查询）
     */
    @JsonProperty("name")
    private String name;

    /**
     * 部署状态筛选
     * 可选值：
     * - running: 筛选部署中的记录
     * - stopped: 筛选已停止的记录
     * - 空: 筛选全部记录
     */
    @JsonProperty("status")
    private String status;

    /**
     * 部署UUID（选填）
     */
    @JsonProperty("deployment_uuid")
    private String deploymentUuid;
}
