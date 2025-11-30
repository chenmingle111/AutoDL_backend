package com.autodl_backend.local.interceptor;

import com.autodl_backend.local.pojo.response.ApiResponse;
import com.autodl_backend.local.pojo.response.ResponseCode;
import com.autodl_backend.util.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 通用拦截器，处理token验证
 */
@Component
public class GeneralInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 预处理回调方法
     * 在请求处理之前进行调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 打印请求路径
        System.out.println("全局拦截器preHandle: " + request.getRequestURI());

        // 从请求头获取token
        String token = request.getHeader("Authorization");

        // 验证token并获取uid
        if (token != null && tokenUtil.isTokenValid(token)) {
            String uid = tokenUtil.getUidFromToken(token);
            // 将uid存入请求属性中，供后续使用
            request.setAttribute("uid", uid);
            return true;
        }

        //调用apiResponse的error方法，返回错误信息
        ApiResponse<Object> apiResponse = ApiResponse.error(ResponseCode.UNAUTHORIZED, "token无权限");
        //序列化响应结果返回给前端
        response.getWriter().write(new ObjectMapper().writeValueAsString(apiResponse));
        return false;
    }

    /**
     * 后处理回调方法
     * 在请求处理之后，视图渲染之前调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // 此处可添加后处理逻辑
    }

    /**
     * 整个请求处理完毕回调方法
     * 在视图渲染完毕时调用
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // 此处可添加请求完成后的逻辑
    }
}
