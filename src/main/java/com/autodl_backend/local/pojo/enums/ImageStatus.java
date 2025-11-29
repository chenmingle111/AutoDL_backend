package com.autodl_backend.local.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ImageStatus {
    CREATING("creating"),
    READY("ready"),
    FINISHED("finished"),
    ERROR("error");

    @EnumValue
    @JsonValue
    private final String value;

    ImageStatus(String value) {
        this.value = value;
    }
}
