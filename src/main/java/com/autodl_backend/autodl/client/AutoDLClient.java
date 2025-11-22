package com.autodl_backend.autodl.client;

import com.autodl_backend.autodl.config.AutoDLProperties;
import com.autodl_backend.autodl.dto.*;
import com.autodl_backend.autodl.dto.container.ContainerEventsReq;
import com.autodl_backend.autodl.dto.container.ContainerListData;
import com.autodl_backend.autodl.dto.container.ContainerListReq;
import com.autodl_backend.autodl.dto.container.ContainerStopReq;
import com.autodl_backend.autodl.dto.container.ContainerEventData;
import com.autodl_backend.autodl.exception.AutoDLException;
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
        headers.set("Content-Type", "application/json");
        HttpEntity<Object> entity = new HttpEntity<>(req, headers);

        ResponseEntity<AutoDLResp<T>> response = restTemplate.exchange(url, HttpMethod.POST, entity, typeReference);
        AutoDLResp<T> result = response.getBody();

        // Check for null response
        if (result == null) {
            throw new AutoDLException("AutoDL API returned null response");
        }

        // Check for error code
        if (!"Success".equals(result.getCode())) {
            throw new AutoDLException(result.getCode(), result.getMsg());
        }

        return result;
    }

    public <T> AutoDLResp<T> put(String path, Object req, ParameterizedTypeReference<AutoDLResp<T>> typeReference) {
        String url = autoDLProperties.getUrl() + path;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", autoDLProperties.getToken());
        headers.set("Content-Type", "application/json");
        HttpEntity<Object> entity = new HttpEntity<>(req, headers);

        ResponseEntity<AutoDLResp<T>> response = restTemplate.exchange(url, HttpMethod.PUT, entity, typeReference);
        AutoDLResp<T> result = response.getBody();

        // Check for null response
        if (result == null) {
            throw new AutoDLException("AutoDL API returned null response");
        }

        // Check for error code
        if (!"Success".equals(result.getCode())) {
            throw new AutoDLException(result.getCode(), result.getMsg());
        }

        return result;
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

    /**
     * Query container events
     */
    public ContainerEventData getContainerEvents(ContainerEventsReq req) {
        AutoDLResp<ContainerEventData> resp = post("/dev/deployment/container/event/list", req,
                new ParameterizedTypeReference<AutoDLResp<ContainerEventData>>() {});
        return resp.getData();
    }

    /**
     * Query container list
     */
    public ContainerListData getContainerList(ContainerListReq req) {
        AutoDLResp<ContainerListData> resp = post("/dev/deployment/container/list", req,
                new ParameterizedTypeReference<AutoDLResp<ContainerListData>>() {});
        return resp.getData();
    }

    /**
     * Stop a container
     */
    public Object stopContainer(ContainerStopReq req) {
        AutoDLResp<Object> resp = put("/dev/deployment/container/stop", req,
                new ParameterizedTypeReference<AutoDLResp<Object>>() {});
        return resp.getData();
    }
}
