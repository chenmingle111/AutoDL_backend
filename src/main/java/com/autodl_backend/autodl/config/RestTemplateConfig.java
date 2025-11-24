package com.autodl_backend.autodl.config;

import com.autodl_backend.config.ConfigUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import java.time.Duration;
import java.util.Collections;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // 从全局配置中获取超时设置
        int connectTimeout = ConfigUtils.getApiConfig() != null ? 
                            ConfigUtils.getApiConfig().getConnectTimeout() : 5000;
        int readTimeout = ConfigUtils.getApiConfig() != null ? 
                         ConfigUtils.getApiConfig().getReadTimeout() : 10000;
        
        // 创建带有超时设置的RestTemplate
        RestTemplate restTemplate = builder
                .setConnectTimeout(Duration.ofMillis(connectTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
        
        // 添加请求拦截器，可用于添加通用头部、日志记录等
        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            // 添加AutoDL API Token（如果存在）
            String token = ConfigUtils.getAutoDLConfig() != null ? 
                          ConfigUtils.getAutoDLConfig().getToken() : null;
            if (token != null) {
                request.getHeaders().set("Authorization", "Token " + token);
            }
            
            // 可以添加其他通用头部或日志记录
            request.getHeaders().set("Content-Type", "application/json");
            request.getHeaders().set("Accept", "application/json");
            
            return execution.execute(request, body);
        };
        
        restTemplate.setInterceptors(Collections.singletonList(interceptor));
        
        return restTemplate;
    }
}

