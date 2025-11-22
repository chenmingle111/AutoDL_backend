package com.autodl_backend.local.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ContainerStatus {
    CREATING("creating"),
    RUNNING("running"),
    SHUTDOWN("shutdown"),
    ERROR("error");

    @EnumValue
    @JsonValue
    private final String value;

    ContainerStatus(String value) {
        this.value = value;
    }
}
