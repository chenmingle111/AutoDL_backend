package com.autodl_backend.local.aspect;

import com.autodl_backend.local.pojo.response.ApiResponse;
import com.autodl_backend.local.pojo.response.ResponseCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
@Slf4j
public class InvalidContainerUUIDAspect {

    /**
     * 捕获ContainerUUID异常并且转换为恰当的http响应
     */
    @Around("execution(* com.autodl_backend.local.service.impl..*.*(..))")
    public Object HandleContainerUUIDException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            log.error("ContainerUUID异常:{}", e.getMessage());

            // 获取响应对象
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletResponse response = attributes.getResponse();
            //调用apiResponse的error方法，返回错误信息
            ApiResponse<Object> apiResponse = ApiResponse.error(ResponseCode.ERROE, e.getMessage());
            //序列化响应结果返回给前端
            response.getWriter().write(new ObjectMapper().writeValueAsString(apiResponse));
        }
        return null;
    }
}
