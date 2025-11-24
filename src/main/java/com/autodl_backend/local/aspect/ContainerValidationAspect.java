package com.autodl_backend.local.aspect;

import com.autodl_backend.local.exception.ContainerAlreadyExistsException;
import com.autodl_backend.local.exception.ContainerCreationFailedException;
import com.autodl_backend.local.exception.ContainerNotFoundException;
import com.autodl_backend.local.exception.ContainerStartFailedException;
import com.autodl_backend.local.exception.ContainerStatusException;
import com.autodl_backend.local.exception.ContainerStopFailedException;
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
 * 容器验证切面
 * 用于处理容器相关的错误
 */
@Aspect
@Component
public class ContainerValidationAspect {

    private static final Logger logger = LoggerFactory.getLogger(ContainerValidationAspect.class);

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 拦截容器控制器方法，处理容器相关异常
     */
    @Around("execution(* com.autodl_backend.local.controller.user.ContainerController.*(..))")
    public Object handleContainerExceptions(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // 执行原始方法
            return joinPoint.proceed();
        } catch (ContainerNotFoundException ex) {
            // 处理容器不存在异常
            logger.warn("容器不存在: {}", ex.getMessage());
            return errorResponse(HttpServletResponse.SC_NOT_FOUND, ex.getMessage());
        } catch (ContainerAlreadyExistsException ex) {
            // 处理容器已存在异常
            logger.warn("容器已存在: {}", ex.getMessage());
            return errorResponse(HttpServletResponse.SC_CONFLICT, ex.getMessage());
        } catch (ContainerCreationFailedException ex) {
            // 处理容器创建失败异常
            logger.error("容器创建失败: {}", ex.getMessage());
            return errorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ContainerStartFailedException ex) {
            // 处理容器启动失败异常
            logger.error("容器启动失败: {}", ex.getMessage());
            return errorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ContainerStopFailedException ex) {
            // 处理容器停止失败异常
            logger.error("容器停止失败: {}", ex.getMessage());
            return errorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        } catch (ContainerStatusException ex) {
            // 处理容器状态异常
            logger.warn("容器状态异常: {}", ex.getMessage());
            return errorResponse(HttpServletResponse.SC_CONFLICT, ex.getMessage());
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
        } else if (statusCode == HttpServletResponse.SC_CONFLICT) {
            apiResponse = ApiResponse.error("Conflict", message);
        } else {
            apiResponse = ApiResponse.error("ContainerError", message);
        }

        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
        return null;
    }
}
