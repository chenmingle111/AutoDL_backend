package com.autodl_backend.local.aspect;

import com.autodl_backend.autodl.dto.deployment.BlacklistReq;
import com.autodl_backend.local.exception.UserDisabledException;
import com.autodl_backend.local.exception.UserInBlacklistException;
import com.autodl_backend.local.pojo.entity.Users;
import com.autodl_backend.local.pojo.response.ApiResponse;
import com.autodl_backend.local.service.UsersService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户状态检查切面
 * 用于检查用户是否被禁用或是否在调度黑名单中
 */
@Aspect
@Component
public class UserStatusAspect {

    private static final Logger logger = LoggerFactory.getLogger(UserStatusAspect.class);

    @Autowired
    private UsersService usersService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 拦截需要用户验证的控制器方法，检查用户状态
     */
    @Around("execution(* com.autodl_backend.local.controller.user..*(..)) || " +
            "execution(* com.autodl_backend.local.controller.admin..*(..))")
    public Object checkUserStatus(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取当前请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return joinPoint.proceed();
        }

        HttpServletRequest request = attributes.getRequest();

        // 从请求中获取当前用户
        Users currentUser = (Users) request.getAttribute("currentUser");
        if (currentUser == null) {
            // 如果没有当前用户信息，可能是未认证的请求，直接执行原方法
            return joinPoint.proceed();
        }

        // 检查用户是否被禁用
        if (currentUser.getIsDeleted() == 1) {
            return handleDisabledUser(attributes.getResponse());
        }

        // 检查用户是否在调度黑名单中
        try {
            BlacklistReq blacklist = usersService.getBlacklist();
            if (blacklist != null && blacklist.getDeploymentContainerUuid() != null) {
                // 这里简化处理，实际可能需要更复杂的逻辑来判断用户是否在黑名单中
                // 例如，检查用户的容器UUID是否在黑名单中
                if (isUserInBlacklist(currentUser, blacklist)) {
                    return handleUserInBlacklist(attributes.getResponse());
                }
            }
        } catch (Exception e) {
            logger.error("检查用户黑名单状态时出错: {}", e.getMessage());
            // 如果检查黑名单出错，记录日志但不阻止请求继续
        }

        // 用户状态正常，继续执行原方法
        return joinPoint.proceed();
    }

    /**
     * 检查用户是否在黑名单中
     * 这里简化处理，实际可能需要更复杂的逻辑
     */
    private boolean isUserInBlacklist(Users user, BlacklistReq blacklist) {
        // 这里只是一个示例，实际实现可能需要查询用户的容器信息并与黑名单比较
        // 例如，检查用户的容器UUID是否在黑名单中
        return false; // 暂时返回false，实际实现需要根据业务逻辑调整
    }

    /**
     * 处理被禁用用户的响应
     */
    private Object handleDisabledUser(HttpServletResponse response) throws IOException {
        logger.warn("尝试访问被禁用的用户账户");

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        ApiResponse<Object> apiResponse = ApiResponse.forbidden("用户账户已被管理员禁用");
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
        return null;
    }

    /**
     * 处理在黑名单中用户的响应
     */
    private Object handleUserInBlacklist(HttpServletResponse response) throws IOException {
        logger.warn("尝试访问在调度黑名单中的用户");

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        ApiResponse<Object> apiResponse = ApiResponse.forbidden("用户在调度黑名单中，无法执行此操作");
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
        return null;
    }
}
