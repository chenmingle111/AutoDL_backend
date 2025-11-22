package com.autodl_backend.integration.DTO;

import com.autodl_backend.pojo.Images;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AutoDLImageListData {
    private List<Images> list;

    @JsonProperty("page_index")
    private Integer pageIndex;

    @JsonProperty("page_size")
    private Integer pageSize;

    @JsonProperty("max_page")
    private Integer maxPage;

    private Integer offset;

    @JsonProperty("result_total")
    private Long resultTotal;
}
