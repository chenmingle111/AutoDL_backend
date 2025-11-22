package com.autodl_backend.autodl.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for AutoDL.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "autodl.api")
public class AutoDLProperties {
    private String url = "https://www.autodl.com/api/v1";
    private String token;
}
