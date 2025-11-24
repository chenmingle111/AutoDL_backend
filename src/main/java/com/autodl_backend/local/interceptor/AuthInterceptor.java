package com.autodl_backend.local.interceptor;

import com.autodl_backend.local.pojo.entity.Users;
import com.autodl_backend.local.pojo.enums.UserRole;
import com.autodl_backend.local.pojo.response.ApiResponse;
import com.autodl_backend.local.service.UsersService;
import com.autodl_backend.util.TokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 认证拦截器
 * 用于验证用户token和权限
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    // 需要管理员权限的路径
    private static final List<String> ADMIN_PATHS = Arrays.asList(
        "/api/admin"
    );

    // 需要用户权限的路径
    private static final List<String> USER_PATHS = Arrays.asList(
        "/api/user",
        "/api/deploy",
        "/api/containers",
        "/api/images"
    );

    // 不需要验证的路径
    private static final List<String> EXCLUDE_PATHS = Arrays.asList(
        "/api/auth/login",
        "/api/auth/register",
        "/api/public",
        "/error"
    );

    @Autowired
    private UsersService usersService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String requestURI = request.getRequestURI();

        // 检查是否为排除路径
        if (isExcludePath(requestURI)) {
            return true;
        }

        // 获取Authorization header
        String authToken = request.getHeader("Authorization");
        if (authToken == null || authToken.isEmpty()) {
            // 尝试从参数中获取
            authToken = request.getParameter("auth_token");
        }

        // 验证token是否为空
        if (authToken == null || authToken.isEmpty()) {
            return unauthorizedResponse(response, "认证令牌缺失");
        }

        // 验证token格式
        if (!TokenManager.validateToken(authToken)) {
            return unauthorizedResponse(response, "无效的认证令牌");
        }

        // 查询用户
        Users user = usersService.findByAuthToken(authToken);

        // 验证用户是否存在
        if (user == null) {
            return unauthorizedResponse(response, "用户不存在或令牌无效");
        }

        // 验证用户是否被禁用
        if (user.getIsDeleted() == 1) {
            return unauthorizedResponse(response, "用户账户已被禁用");
        }

        // 检查路径权限
        if (isAdminPath(requestURI) && user.getRole() != UserRole.ADMIN) {
            return forbiddenResponse(response, "权限不足：需要管理员权限");
        }

        if (isUserPath(requestURI) && user.getRole() == UserRole.TOURIST) {
            return forbiddenResponse(response, "权限不足：需要用户权限");
        }

        // 将用户信息存入请求属性，供后续使用
        request.setAttribute("currentUser", user);

        return true;
    }

    /**
     * 检查是否为排除路径
     */
    private boolean isExcludePath(String requestURI) {
        return EXCLUDE_PATHS.stream().anyMatch(requestURI::startsWith);
    }

    /**
     * 检查是否为管理员路径
     */
    private boolean isAdminPath(String requestURI) {
        return ADMIN_PATHS.stream().anyMatch(requestURI::startsWith);
    }

    /**
     * 检查是否为用户路径
     */
    private boolean isUserPath(String requestURI) {
        return USER_PATHS.stream().anyMatch(requestURI::startsWith);
    }

    /**
     * 返回未授权响应
     */
    private boolean unauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        logger.warn("认证失败: {}", message);
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ApiResponse<Object> apiResponse = ApiResponse.unauthorized(message);
        response.getWriter().write(new ObjectMapper().writeValueAsString(apiResponse));
        return false;
    }

    /**
     * 返回禁止访问响应
     */
    private boolean forbiddenResponse(HttpServletResponse response, String message) throws IOException {
        logger.warn("权限不足: {}", message);
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        ApiResponse<Object> apiResponse = ApiResponse.forbidden(message);
        response.getWriter().write(new ObjectMapper().writeValueAsString(apiResponse));
        return false;
    }
}
