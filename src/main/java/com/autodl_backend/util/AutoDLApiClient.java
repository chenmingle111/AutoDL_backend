package com.autodl_backend.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * AutoDL API客户端工具类
 * 用于调用AutoDL私有云API
 */
@Slf4j
@Component
public class AutoDLApiClient {

    private static final String API_HOST = "https://private.autodl.com";

    private final RestTemplate restTemplate;

    public AutoDLApiClient() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * 发送POST请求到AutoDL API
     * 
     * @param endpoint     API端点路径
     * @param token        认证token
     * @param requestBody  请求体
     * @param responseType 响应类型
     * @return 响应对象
     */
    public <T> T post(String endpoint, String token, Object requestBody, Class<T> responseType) {
        try {
            String url = API_HOST + endpoint;

            // 设置请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", token);

            // 创建请求实体
            HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);

            log.info("调用AutoDL API: {} 请求参数: {}", url, requestBody);

            // 发送请求
            ResponseEntity<T> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    requestEntity,
                    responseType);

            log.info("AutoDL API响应状态: {}", response.getStatusCode());

            return response.getBody();

        } catch (Exception e) {
            log.error("调用AutoDL API失败: {}", e.getMessage(), e);
            throw new RuntimeException("调用AutoDL API失败: " + e.getMessage(), e);
        }
    }
}
