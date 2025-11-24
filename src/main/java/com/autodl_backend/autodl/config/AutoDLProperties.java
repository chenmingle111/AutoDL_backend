```
package com.autodl_backend.autodl.config;

import com.autodl_backend.config.ConfigUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for AutoDL.
 * 注意：此类已迁移至 GlobalConfig 统一管理，此处保留向后兼容
 */
@Data
@Component
public class AutoDLProperties {
    /**
     * API URL
     */
    public String getUrl() {
        return ConfigUtils.getAutoDLConfig() != null ? ConfigUtils.getAutoDLConfig().getUrl() : "https://www.autodl.com/api/v1";
    }
    
    /**
     * API token
     */
    public String getToken() {
        return ConfigUtils.getAutoDLConfig() != null ? ConfigUtils.getAutoDLConfig().getToken() : null;
    }
    
    // 为保持向后兼容性，提供setter方法，但实际上值会从GlobalConfig获取
    public void setUrl(String url) {
        // 不执行任何操作，值由GlobalConfig控制
    }
    
    public void setToken(String token) {
        // 不执行任何操作，值由GlobalConfig控制
    }
}
```
}
