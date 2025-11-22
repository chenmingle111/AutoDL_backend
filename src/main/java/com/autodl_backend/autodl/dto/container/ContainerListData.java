package com.autodl_backend.autodl.dto.container;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

/**
 * Data DTO for container list response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerListData {
    private List<ContainerListItem> list;

    @JsonProperty("page_index")
    private Integer pageIndex;

    @JsonProperty("page_size")
    private Integer pageSize;

    @JsonProperty("max_page")
    private Integer maxPage;

    private Integer offset;
}
