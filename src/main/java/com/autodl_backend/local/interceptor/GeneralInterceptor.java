package com.autodl_backend.local.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用拦截器示例
 * <p>
 * 使用说明：
 * 1. 在 preHandle 中编写请求处理前的逻辑（如：身份验证、日志记录、参数检查）。
 * - 返回 true：继续执行后续的拦截器或 Controller。
 * - 返回 false：中断请求，不再执行后续操作。
 * 2. 在 postHandle 中编写 Controller 执行后、视图渲染前的逻辑（如：修改 ModelAndView）。
 * 3. 在 afterCompletion 中编写请求完成后的逻辑（如：资源清理、异常处理日志）。
 * <p>
 * 配置说明：
 * 需要在 WebMvcConfig 中注册此拦截器，并配置拦截路径。
 */
@Component
public class GeneralInterceptor implements HandlerInterceptor {

    /**
     * 预处理回调方法
     * 在请求处理之前进行调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO: 在此处添加你的业务逻辑
        // 示例：打印请求路径
        System.out.println("preHandle-请求路径: " + request.getRequestURI());

        // 如果需要拦截请求，返回 false；否则返回 true
        return true;
    }

    /**
     * 后处理回调方法
     * 在请求处理之后，视图渲染之前调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO: 在此处添加你的业务逻辑
        // System.out.println("GeneralInterceptor - postHandle");
    }

    /**
     * 整个请求处理完毕回调方法
     * 在视图渲染完毕时调用
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO: 在此处添加你的业务逻辑
        // System.out.println("GeneralInterceptor - afterCompletion");
    }
}
