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
    private String url = "https://api.autodl.com/api/v1";
    private String token = "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9." +
            "eyJ1aWQiOjM3Njg4LCJ1dWlkIjoiOWU2YTU2Y2ItYWUzYi00NjVi" +
            "LWFkZDAtYWZmNjJiMDJkYzAyIiwiaXNfYWRtaW4iOmZhbHNlLCJi" +
            "YWNrc3RhZ2Vfcm9sZSI6IiIsImlzX3N1cGVyX2FkbWluIjpmYWxz" +
            "ZSwic3ViX25hbWUiOiIiLCJ0ZW5hbnQiOiJhdXRvZGwiLCJ1cGsi" +
            "OiIifQ.skyJ7eSSrXbb0ye_gcN3krDzoNgoemc0W4qODEg0f7fEq" +
            "rfYvPdGhQTdP353kPHf44u-6HsCNBYP17nm1fajxQ";
}
