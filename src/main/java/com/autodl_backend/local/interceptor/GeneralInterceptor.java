package com.autodl_backend.local.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用拦截器 - 用于记录API请求日志
 */
@Component
public class GeneralInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(GeneralInterceptor.class);

/**
 *记录请求信息和设置请求开始时间
 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 记录请求开始时间，用于后续计算请求处理耗时
        request.setAttribute("startTime", System.currentTimeMillis());

        // 记录请求信息
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String clientIp = getClientIp(request);

        logger.info("请求开始 - IP: {}, 方法: {}, URI: {}", clientIp, method, uri);

        return true;
    }

/**
 * 记录请求处理信息和异常情况
 */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 获取请求开始时间并计算处理时间
        Long startTime = (Long) request.getAttribute("startTime");
        long processingTime = startTime != null ? System.currentTimeMillis() - startTime : 0;

        // 记录响应状态和处理时间
        String method = request.getMethod();            // 获取请求方法
        String uri = request.getRequestURI();           // 获取请求URI
        String clientIp = getClientIp(request);
        int status = response.getStatus();

        if (ex != null) {         // 获取客户端IP地址
            logger.error("请求异常 - IP: {}, 方法: {}, URI: {}, 状态: {}, 耗时: {}ms", 
                clientIp, method, uri, status, processingTime);              // 获取响应状态码
        } else {
    // 根据是否有异常选择不同的日志级别记录信息
            logger.info("请求完成 - IP: {}, 方法: {}, URI: {}, 状态: {}, 耗时: {}ms",
                clientIp, method, uri, status, processingTime);
        }
    }

    /**
     * 获取客户端真实IP
 * 该方法会依次检查多个可能的HTTP头部，以获取客户端的真实IP地址
 * 检查顺序为：
 * 1. x-forwarded-for 头部
 * 2. Proxy-Client-IP 头部
 * 3. WL-Proxy-Client-IP 头部
 * 4. 如果以上都没有，则使用request.getRemoteAddr()获取
 * 如果IP地址包含多个IP（以逗号分隔），则只取第一个IP
 * @param request HttpServletRequest对象，包含请求的所有信息
 * @return 返回客户端的真实IP地址
     */
    private String getClientIp(HttpServletRequest request) {
    // 首先尝试从x-forwarded-for头部获取IP
        String ip = request.getHeader("x-forwarded-for");
    // 如果获取的IP为空、空字符串或"unknown"，则尝试下一个可能的头部
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
    // 如果仍然没有获取到有效的IP，则尝试WL-Proxy-Client-IP头部
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
    // 如果以上头部都没有有效IP，则使用request.getRemoteAddr()获取IP
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 如果是多个IP，取第一个
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }

        return ip;
    }
}
