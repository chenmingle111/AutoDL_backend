package com.autodl_backend.local.aspect;

import com.autodl_backend.local.exception.UserNotFoundException;
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

/**
 * 用户验证切面
 * 用于处理业务操作中的用户不存在异常
 */
@Aspect
@Component
public class UserValidationAspect {

    private static final Logger logger = LoggerFactory.getLogger(UserValidationAspect.class);

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 捕获UserNotFoundException异常并转换为适当的HTTP响应
     */
    @Around("execution(* com.autodl_backend.local.controller..*(..))")
    public Object handleUserNotFoundException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // 执行原始方法
            return joinPoint.proceed();
        } catch (UserNotFoundException ex) {
            // 处理用户不存在异常
            logger.warn("用户不存在: {}", ex.getMessage());

            // 获取响应对象
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletResponse response = attributes.getResponse();

            if (response != null) {
                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);

                ApiResponse<Object> apiResponse = ApiResponse.notFound(ex.getMessage());
                response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
            }

            return null;
        }
    }
}
