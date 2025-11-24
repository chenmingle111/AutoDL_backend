package com.autodl_backend.local.config;

import com.autodl_backend.config.ConfigUtils;
import com.autodl_backend.local.interceptor.AuthInterceptor;
import com.autodl_backend.local.interceptor.GeneralInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC 配置类
 * 用于注册拦截器和CORS等Web相关配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private GeneralInterceptor generalInterceptor;

    @Autowired
    private AuthInterceptor authInterceptor;

    /**
     * 添加自定义的拦截器，并配置拦截规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册认证拦截器，优先级高于通用拦截器
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**") // 拦截所有请求
                .order(1); // 设置拦截器顺序，数字越小优先级越高

        // 注册通用拦截器
        registry.addInterceptor(generalInterceptor)
                .addPathPatterns("/**") // 拦截所有请求
                .order(2); // 设置拦截器顺序，数字越小优先级越高
    }
    
    /**
     * 配置CORS（跨域资源共享）
     * 使用GlobalConfig中的安全配置来控制CORS行为
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 从全局配置中获取CORS配置
        boolean corsEnabled = ConfigUtils.getSecurityConfig() != null ? 
                              ConfigUtils.getSecurityConfig().isCorsEnabled() : true;
        
        if (corsEnabled) {
            String allowedOrigins = ConfigUtils.getSecurityConfig() != null ? 
                                   ConfigUtils.getSecurityConfig().getAllowedOrigins() : "*";
            
            registry.addMapping("/**")
                    .allowedOrigins(allowedOrigins.split(","))
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("*")
                    .allowCredentials(true)
                    .maxAge(3600);
        }
    }
}
