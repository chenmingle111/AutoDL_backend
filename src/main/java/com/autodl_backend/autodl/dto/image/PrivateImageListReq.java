package com.autodl_backend.autodl.dto.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PrivateImageListReq {
    @JsonProperty(value = "page_index",required = true)
    private Integer pageIndex;

    @JsonProperty(value = "page_size",required = true)
    private Integer pageSize;

    private Integer offset;
}
