package com.autodl_backend.autodl.dto.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class PrivateImageListData {
    private List<PrivateImageItem> list;

    @JsonProperty("page_index")
    private Integer pageIndex;

    @JsonProperty("page_size")
    private Integer pageSize;

    @JsonProperty("max_page")
    private Integer maxPage;

    private Integer offset;
}
