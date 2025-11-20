package com.autodl_backend.pojo.response;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 响应代码常量
 */
public class ResponseCode {
    public static final String SUCCESS = "Success";
    public static final String INVALID_PARAM = "InvalidParameter";
    public static final String UNAUTHORIZED = "Unauthorized";
    public static final String NOT_FOUND = "NotFound";
    public static final String INSUFFICIENT_BALANCE = "InsufficientBalance";
    public static final String RESOURCE_EXHAUSTED = "ResourceExhausted";
    public static final String INTERNAL_ERROR = "InternalError";
    public static final String PERMISSION_DENIED = "PermissionDenied";
}