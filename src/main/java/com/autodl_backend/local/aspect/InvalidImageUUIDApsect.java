package com.autodl_backend.local.aspect;


import com.autodl_backend.local.pojo.response.ApiResponse;
import com.autodl_backend.local.pojo.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class InvalidImageUUIDApsect {

    /**
     * 捕获ImageUUID异常并且转换为恰当的http响应
     */
    @Around("execution(* com.autodl_backend.local.service.impl..*.*(..))")
    public Object HandleImageUUIDException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            log.error("ImageUUID异常:{}", e.getMessage());
            ApiResponse.error(ResponseCode.ERROE, e.getMessage());
        }
        return null;
    }
}
