package com.autodl_backend.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DeploymentType {
    REPLICA_SET("ReplicaSet"),
    JOB("Job"),
    CONTAINER("Container");

    @EnumValue
    @JsonValue
    private final String value;

    DeploymentType(String value) {
        this.value = value;
    }
}
