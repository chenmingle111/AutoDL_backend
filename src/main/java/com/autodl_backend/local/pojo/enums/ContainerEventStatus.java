package com.autodl_backend.local.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ContainerEventStatus {
    CREATED("created"),
    CREATING("creating"),
    STARTING("starting"),
    RUNNING("running"),
    SHUTTING_DOWN("shutting_down"),
    SHUTDOWN("shutdown"),
    OSSMERGED("oss_merged"),
    ERROR("error");

    @EnumValue
    @JsonValue
    private final String value;

    ContainerEventStatus(String value) {
        this.value = value;
    }
}
