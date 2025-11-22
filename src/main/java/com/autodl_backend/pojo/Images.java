package com.autodl_backend.pojo;

import com.autodl_backend.pojo.enums.ImageStatus;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("images")
public class Images {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("uid")
    private Integer uid; // 归属用户ID

    @TableField("image_uuid")
    @JsonProperty("image_uuid")
    private String imageUuid; // 镜像唯一标识

    @TableField("image_name")
    @JsonProperty("image_name")
    private String imageName; // 镜像名称

    @TableField("status")
    private ImageStatus status; // 镜像状态

    @TableField("image_size")
    private Long imageSize; // 镜像大小（字节）

    @TableField("description")
    private String description; // 镜像描述

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @TableLogic
    @TableField("is_deleted")
    private Integer isDeleted;
}
