package com.autodl_backend.local.pojo.response;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 响应代码常量
 */
public class ResponseCode {
    // 基础状态码
    public static final String SUCCESS = "Success";
    public static final String ERROE = "Error";

    // 具体业务状态码
    //不合法参数
    public static final String INVALID_PARAM = "InvalidParameter";
    //未授权
    public static final String UNAUTHORIZED = "Unauthorized";
    //资源未找到
    public static final String NOT_FOUND = "NotFound";
    //余额不足
    public static final String INSUFFICIENT_BALANCE = "InsufficientBalance";
    //内部错误
    public static final String INTERNAL_ERROR = "InternalError";
}