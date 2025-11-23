package com.autodl_backend.local.config;

import com.autodl_backend.local.interceptor.GeneralInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC 配置类
 * 用于注册拦截器等
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private GeneralInterceptor generalInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册通用拦截器
        registry.addInterceptor(generalInterceptor)
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns("/api/login", "/api/register"); // 排除登录注册等接口（根据实际情况调整）
    }
}
