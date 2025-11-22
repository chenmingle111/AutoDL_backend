package com.autodl_backend.autodl.dto.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PrivateImageListReq {
    @JsonProperty("page_index")
    private Integer pageIndex;

    @JsonProperty("page_size")
    private Integer pageSize;

    private Integer offset;
}
