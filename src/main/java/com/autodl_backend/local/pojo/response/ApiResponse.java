package com.autodl_backend.local.pojo.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private String code;
    private String msg;
    private T data;

    /**
     * 成功响应
     */
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ResponseCode.SUCCESS, "", data);
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(ResponseCode.SUCCESS, "", null);
    }

    /**
     * 错误响应
     */
    public static <T> ApiResponse<T> error(String code, String msg) {
        return new ApiResponse<>(code, msg, null);
    }

    /**
     * 参数错误
     */
    public static <T> ApiResponse<T> invalidParam(String msg) {
        return error(ResponseCode.INVALID_PARAM, msg);
    }

    /**
     * 未授权
     */
    public static <T> ApiResponse<T> unauthorized(String msg) {
        return error(ResponseCode.UNAUTHORIZED, msg);
    }

    /**
     * 禁止访问
     */
    public static <T> ApiResponse<T> forbidden(String msg) {
        return error(ResponseCode.PERMISSION_DENIED, msg);
    }

    /**
     * 资源未找到
     */
    public static <T> ApiResponse<T> notFound(String msg) {
        return error(ResponseCode.NOT_FOUND, msg);
    }

    /**
     * 内部错误
     */
    public static <T> ApiResponse<T> internalError(String msg) {
        return error(ResponseCode.INTERNAL_ERROR, msg);
    }
}
