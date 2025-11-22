package com.autodl_backend.autodl.dto.container;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

/**
 * Data DTO for container events response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerEventData {
    private List<ContainerEventItem> list;

    @JsonProperty("page_index")
    private Integer pageIndex;

    @JsonProperty("page_size")
    private Integer pageSize;

    @JsonProperty("max_page")
    private Integer maxPage;

    private Integer offset;
}
