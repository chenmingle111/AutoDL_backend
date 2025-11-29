package com.autodl_backend.local.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MachinesStatus {
    SHUTDOWN("shutdown"),
    ACTIVE("active"),
    INACTIVE("inactive"),
    MAINTENANCE("maintenance");

    @EnumValue // MyBatis-Plus映射数据库值
    @JsonValue // JSON序列化返回值
    private final String value;

    MachinesStatus(String value) {
        this.value = value;
    }
}
