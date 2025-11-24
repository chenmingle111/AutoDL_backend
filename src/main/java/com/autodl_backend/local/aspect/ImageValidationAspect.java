package com.autodl_backend.local.aspect;

import com.autodl_backend.local.exception.ImageNotFoundException;
import com.autodl_backend.local.exception.ImagePullFailedException;
import com.autodl_backend.local.exception.ImagePermissionDeniedException;
import com.autodl_backend.local.exception.UnsupportedImageFormatException;
import com.autodl_backend.local.pojo.response.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 镜像验证切面
 * 用于处理镜像相关的错误
 */
@Aspect
@Component
public class ImageValidationAspect {

    private static final Logger logger = LoggerFactory.getLogger(ImageValidationAspect.class);

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 拦截镜像控制器方法，处理镜像相关异常
     */
    @Around("execution(* com.autodl_backend.local.controller.user.ImageController.*(..))")
    public Object handleImageExceptions(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // 执行原始方法
            return joinPoint.proceed();
        } catch (ImageNotFoundException ex) {
            // 处理镜像不存在异常
            logger.warn("镜像不存在: {}", ex.getMessage());
            return errorResponse(HttpServletResponse.SC_NOT_FOUND, ex.getMessage());
        } catch (ImagePullFailedException ex) {
            // 处理镜像拉取失败异常
            logger.error("镜像拉取失败: {}", ex.getMessage());
            return errorResponse(HttpServletResponse.SC_BAD_GATEWAY, ex.getMessage());
        } catch (UnsupportedImageFormatException ex) {
            // 处理镜像格式不支持异常
            logger.warn("镜像格式不支持: {}", ex.getMessage());
            return errorResponse(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        } catch (ImagePermissionDeniedException ex) {
            // 处理镜像权限问题异常
            logger.warn("镜像权限问题: {}", ex.getMessage());
            return errorResponse(HttpServletResponse.SC_FORBIDDEN, ex.getMessage());
        }
    }

    /**
     * 返回错误响应
     */
    private Object errorResponse(int statusCode, String message) throws IOException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return null;
        }

        HttpServletResponse response = attributes.getResponse();
        if (response == null) {
            return null;
        }

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(statusCode);

        ApiResponse<Object> apiResponse;
        if (statusCode == HttpServletResponse.SC_NOT_FOUND) {
            apiResponse = ApiResponse.notFound(message);
        } else if (statusCode == HttpServletResponse.SC_FORBIDDEN) {
            apiResponse = ApiResponse.forbidden(message);
        } else {
            apiResponse = ApiResponse.error("ImageError", message);
        }

        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
        return null;
    }
}
