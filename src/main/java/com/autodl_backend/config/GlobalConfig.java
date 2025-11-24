package com.autodl_backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 全局配置类 - 统一管理系统配置
 * 作为配置管理的中心入口，提供对各模块配置的访问
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "autodl")
public class GlobalConfig {

    /**
     * 系统名称
     */
    private String systemName = "AutoDL Backend";
    
    /**
     * 系统版本
     */
    private String version = "1.0.0";
    
    /**
     * API 配置
     */
    private ApiConfig api = new ApiConfig();
    
    /**
     * AutoDL配置
     */
    private AutoDLConfig autodl = new AutoDLConfig();
    
    /**
     * 安全配置
     */
    private SecurityConfig security = new SecurityConfig();
    
    /**
     * 功能配置
     */
    private FeatureConfig feature = new FeatureConfig();
    
    /**
     * API相关配置
     */
    @Data
    public static class ApiConfig {
        /**
         * API基础URL
         */
        private String baseUrl = "";
        
        /**
         * API版本
         */
        private String apiVersion = "v1";
        
        /**
         * 连接超时时间（毫秒）
         */
        private int connectTimeout = 5000;
        
        /**
         * 读取超时时间（毫秒）
         */
        private int readTimeout = 10000;
        
        /**
         * 重试次数
         */
        private int maxRetries = 3;
        
        /**
         * API密钥
         */
        private String apiKey = "";
    }
    
    /**
     * AutoDL特定配置
     */
    @Data
    public static class AutoDLConfig {
        /**
         * AutoDL API URL
         */
        private String url = "https://www.autodl.com/api/v1";
        
        /**
         * AutoDL API token
         */
        private String token;
    }
    
    /**
     * 安全配置
     */
    @Data
    public static class SecurityConfig {
        /**
         * JWT过期时间（分钟）
         */
        private int jwtExpireMinutes = 1440;
        
        /**
         * JWT密钥
         */
        private String jwtSecret = "";
        
        /**
         * 是否启用CORS
         */
        private boolean corsEnabled = true;
        
        /**
         * 允许的跨域来源
         */
        private String allowedOrigins = "*";
    }
    
    /**
     * 功能配置
     */
    @Data
    public static class FeatureConfig {
        /**
         * 是否启用容器管理功能
         */
        private boolean containerManagementEnabled = true;
        
        /**
         * 是否启用自动部署功能
         */
        private boolean autoDeployEnabled = true;
        
        /**
         * 是否启用镜像管理功能
         */
        private boolean imageManagementEnabled = true;
    }
}