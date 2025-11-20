package com.autodl_backend.annotation;

import java.lang.annotation.*;

/**
 * 必需参数验证注解
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiredParams {
    String[] value();
}