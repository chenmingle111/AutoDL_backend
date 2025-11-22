package com.autodl_backend.integration.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AutoDL API镜像列表请求DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoDLImageRequest {

    @JsonProperty("page_index")
    private Integer pageIndex;

    @JsonProperty("page_size")
    private Integer pageSize;

    private Integer offset;
}
