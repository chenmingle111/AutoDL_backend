package com.autodl_backend.integration.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * AutoDL API镜像列表响应DTO
 */
@Data
public class AutoDLImageResponse {

    private String code;
    private String msg;
    private DataWrapper data;

    @Data
    public static class DataWrapper {
        private List<ImageItem> list;

        @JsonProperty("page_index")
        private Integer pageIndex;

        @JsonProperty("page_size")
        private Integer pageSize;

        @JsonProperty("max_page")
        private Integer maxPage;

        private Integer offset;
    }

    @Data
    public static class ImageItem {
        private Integer id;

        @JsonProperty("created_at")
        private String createdAt;

        @JsonProperty("updated_at")
        private String updatedAt;

        @JsonProperty("image_uuid")
        private String imageUuid;

        private String name;

        private String status;
    }
}
