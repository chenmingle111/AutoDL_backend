package com.autodl_backend.autodl.dto.deployment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeploymentListData {

    @JsonProperty("list")
    private List<DeploymentItem> list;

    @JsonProperty("page_index")
    private Integer pageIndex;

    @JsonProperty("page_size")
    private Integer pageSize;

    @JsonProperty("offset")
    private Integer offset;

    @JsonProperty("max_page")
    private Integer maxPage;

    @JsonProperty("result_total")
    private Integer resultTotal;

    @JsonProperty("page")
    private Integer page;
}
