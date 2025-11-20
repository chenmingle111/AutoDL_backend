package com.autodl_backend.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {
    USER("user"),
    ADMIN("admin"),
    TOURIST("tourist");

    @EnumValue // MyBatis-Plus映射数据库值
    @JsonValue // JSON序列化返回值
    private final String value;

    UserRole(String value) {
        this.value = value;
    }
}
