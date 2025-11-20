package com.autodl_backend.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DeploymentStatus {
    CREATING("creating"),
    RUNNING("running"),
    STOPPED("stopped"),
    ERROR("error");

    @EnumValue
    @JsonValue
    private final String value;

    DeploymentStatus(String value) {
        this.value = value;
    }
}
