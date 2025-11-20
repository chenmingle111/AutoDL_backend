package com.autodl_backend.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ContainerEventStatus {
    CREATING("creating"),
    STARTING("starting"),
    RUNNING("running"),
    SHUTTING_DOWN("shutting_down"),
    SHUTDOWN("shutdown"),
    ERROR("error");

    @EnumValue
    @JsonValue
    private final String value;

    ContainerEventStatus(String value) {
        this.value = value;
    }
}
