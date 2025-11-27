
package com.autodl_backend.autodl.dto.deployment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 设置副本数量请求参数
 */
public class ReplicaNumReq {
    /**
     * 部署uuid
     */
    @JsonProperty(value = "deployment_uuid",required = true)
    private String deploymentUuid;

    /**
     * 副本数量。仅支持ReplicaSet的部署类型
     */
    @JsonProperty(value = "replica_num",required = true)
    private Integer replicaNum;
}
