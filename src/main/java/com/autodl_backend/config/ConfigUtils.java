package com.autodl_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 配置工具类
 * 提供静态方法访问全局配置，避免在所有需要配置的地方都注入依赖
 */
@Component
public class ConfigUtils {

    /**
     * 全局配置实例
     */
    private static GlobalConfig globalConfig;
    
    @Autowired
    private GlobalConfig config;
    
    /**
     * 初始化配置工具，在Spring容器启动时注入配置实例
     */
    @PostConstruct
    public void init() {
        ConfigUtils.globalConfig = config;
    }
    
    /**
     * 获取全局配置
     * @return 全局配置实例
     */
    public static GlobalConfig getGlobalConfig() {
        return globalConfig;
    }
    
    /**
     * 获取API配置
     * @return API配置
     */
    public static GlobalConfig.ApiConfig getApiConfig() {
        return globalConfig != null ? globalConfig.getApi() : null;
    }
    
    /**
     * 获取安全配置
     * @return 安全配置
     */
    public static GlobalConfig.SecurityConfig getSecurityConfig() {
        return globalConfig != null ? globalConfig.getSecurity() : null;
    }
    
    /**
     * 获取功能配置
     * @return 功能配置
     */
    public static GlobalConfig.FeatureConfig getFeatureConfig() {
        return globalConfig != null ? globalConfig.getFeature() : null;
    }
    
    /**
     * 获取AutoDL配置
     * @return AutoDL配置
     */
    public static GlobalConfig.AutoDLConfig getAutoDLConfig() {
        return globalConfig != null ? globalConfig.getAutodl() : null;
    }
    
    /**
     * 检查功能是否启用
     * @param featureName 功能名称
     * @return 是否启用
     */
    public static boolean isFeatureEnabled(String featureName) {
        GlobalConfig.FeatureConfig featureConfig = getFeatureConfig();
        if (featureConfig == null) {
            return false;
        }
        
        switch (featureName.toLowerCase()) {
            case "container_management":
                return featureConfig.isContainerManagementEnabled();
            case "auto_deploy":
                return featureConfig.isAutoDeployEnabled();
            case "image_management":
                return featureConfig.isImageManagementEnabled();
            default:
                return false;
        }
    }
    
    /**
     * 获取配置值，如果配置不存在则返回默认值
     * @param propertyName 属性名称
     * @param defaultValue 默认值
     * @return 配置值或默认值
     */
    public static <T> T getProperty(String propertyName, T defaultValue) {
        // 简单实现，后续可扩展支持更复杂的属性路径
        return defaultValue;
    }
}