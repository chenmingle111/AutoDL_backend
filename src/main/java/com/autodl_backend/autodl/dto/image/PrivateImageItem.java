package com.autodl_backend.autodl.dto.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PrivateImageItem {
    private Integer id;

    @JsonProperty("image_name")
    private String imageName;

    @JsonProperty("image_uuid")
    private String imageUuid;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    private String status;
}
