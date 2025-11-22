package com.autodl_backend.autodl.client;

import com.autodl_backend.autodl.config.AutoDLProperties;
import com.autodl_backend.autodl.dto.AutoDLCreateReq;
import com.autodl_backend.autodl.dto.AutoDLResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Client for interacting with AutoDL API.
 * Encapsulates HTTP requests.
 */
@Component
public class AutoDLClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AutoDLProperties autoDLProperties;

    public <T> AutoDLResp<T> post(String path, Object req, ParameterizedTypeReference<AutoDLResp<T>> typeReference) {
        String url = autoDLProperties.getUrl() + path;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", autoDLProperties.getToken());
        HttpEntity<Object> entity = new HttpEntity<>(req, headers);

        ResponseEntity<AutoDLResp<T>> response = restTemplate.exchange(url, HttpMethod.POST, entity, typeReference);
        return response.getBody();
    }

    public AutoDLResp<Object> createInstance(AutoDLCreateReq req) {
        return post("/instance/create", req, new ParameterizedTypeReference<AutoDLResp<Object>>() {
        });
    }

    public AutoDLResp<Object> getInstanceStatus(String instanceId) {
        String url = autoDLProperties.getUrl() + "/instance/" + instanceId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", autoDLProperties.getToken());
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);
        ResponseEntity<AutoDLResp<Object>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<AutoDLResp<Object>>() {
                });
        return response.getBody();
    }
}
