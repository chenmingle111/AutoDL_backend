package com.autodl_backend.autodl.client;

import com.autodl_backend.autodl.config.AutoDLProperties;
import com.autodl_backend.autodl.dto.*;
import com.autodl_backend.autodl.dto.container.ContainerEventsReq;
import com.autodl_backend.autodl.dto.container.ContainerListData;
import com.autodl_backend.autodl.dto.container.ContainerListReq;
import com.autodl_backend.autodl.dto.container.ContainerStopReq;
import com.autodl_backend.autodl.dto.container.ContainerEventData;
import com.autodl_backend.autodl.dto.deployment.BlacklistReq;
import com.autodl_backend.autodl.dto.deployment.CreateDeploymentData;
import com.autodl_backend.autodl.dto.deployment.CreateDeploymentReq;
import com.autodl_backend.autodl.dto.deployment.DeploymentListData;
import com.autodl_backend.autodl.dto.deployment.DeploymentListReq;
import com.autodl_backend.autodl.dto.machines.GpuStockData;
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

    /**
     * 发送POST请求到AutoDL API的通用方法
     */
    public <T> AutoDLResp<T> post(String path, Object req, ParameterizedTypeReference<AutoDLResp<T>> typeReference) {
        // 构建完整的API URL
        String url = autoDLProperties.getUrl() + path;
        // 创建HTTP头部信息
        HttpHeaders headers = new HttpHeaders();
        // 设置授权令牌
        headers.set("Authorization", autoDLProperties.getToken());
        // 设置内容类型为JSON
        headers.set("Content-Type", "application/json");
        // 创建HTTP实体，包含请求体和头部信息
        HttpEntity<Object> entity = new HttpEntity<>(req, headers);

        // 发送POST请求并获取响应
        ResponseEntity<AutoDLResp<T>> response = restTemplate.exchange(url, HttpMethod.POST, entity, typeReference);
        // 从响应中获取响应体
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

    /**
     * 发送PUT请求到AutoDL API并处理响应
     */
    public <T> AutoDLResp<T> put(String path, Object req, ParameterizedTypeReference<AutoDLResp<T>> typeReference) {
        // 构建完整的API URL
        String url = autoDLProperties.getUrl() + path;
        // 设置HTTP请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", autoDLProperties.getToken()); // 设置认证token
        headers.set("Content-Type", "application/json"); // 设置内容类型为JSON
        HttpEntity<Object> entity = new HttpEntity<>(req, headers); // 创建HTTP请求实体，包含请求体和请求头

        ResponseEntity<AutoDLResp<T>> response = restTemplate.exchange(url, HttpMethod.PUT, entity, typeReference); // 发送PUT请求并获取响应
        AutoDLResp<T> result = response.getBody();

        // 创建HTTP请求实体，包含请求体和请求头
        // Check for null response
        if (result == null) {
            throw new AutoDLException("AutoDL API returned null response");
            // 发送PUT请求并获取响应
        }

        // Check for error code
        if (!"Success".equals(result.getCode())) {
            throw new AutoDLException(result.getCode(), result.getMsg());
        }

        return result;
    }

    /**
     * 创建实例的方法
     */
    public AutoDLResp<Object> createInstance(AutoDLCreateReq req) {
        // 发送POST请求到"/instance/create"路径，传入请求参数req
        // 使用ParameterizedTypeReference指定返回类型为AutoDLResp<Object>
        return post("/instance/create", req, new ParameterizedTypeReference<AutoDLResp<Object>>() {
        });
    }

    /**
     * 获取指定实例的状态信息
     */
    public AutoDLResp<Object> getInstanceStatus(String instanceId) {
        // 构建API请求的URL，将基础URL和实例ID拼接
        String url = autoDLProperties.getUrl() + "/instance/" + instanceId;
        // 创建HTTP请求头，设置认证令牌
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", autoDLProperties.getToken());
        // 创建HTTP实体，请求体为null
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);
        ResponseEntity<AutoDLResp<Object>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<AutoDLResp<Object>>() {
                    // 发送GET请求并获取响应，使用ParameterizedTypeReference指定返回类型
                });
        return response.getBody();
    }

    // 返回响应体中的内容
    /**
     * Query container events
     */
    public ContainerEventData getContainerEvents(ContainerEventsReq req) {
        AutoDLResp<ContainerEventData> resp = post("/dev/deployment/container/event/list", req,
                new ParameterizedTypeReference<>() {
                });
        return resp.getData();
    }

    /**
     * Query container list
     */
    public ContainerListData getContainerList(ContainerListReq req) {
        AutoDLResp<ContainerListData> resp = post("/dev/deployment/container/list", req,
                new ParameterizedTypeReference<AutoDLResp<ContainerListData>>() {
                });
        return resp.getData();
    }

    /**
     * Stop a container
     */
    public Object stopContainer(ContainerStopReq req) {
        AutoDLResp<Object> resp = put("/dev/deployment/container/stop", req,
                new ParameterizedTypeReference<>() {
                });
        return resp.getData();
    }

    /**
     * Set scheduling blacklist
     */
    public Object setBlacklist(BlacklistReq req) {
        AutoDLResp<Object> resp = post("/dev/deployment/blacklist", req,
                new ParameterizedTypeReference<AutoDLResp<Object>>() {
                });
        return resp.getData();
    }

    /**
     * Get GPU stock information
     */
    public GpuStockData getGpuStock() {
        AutoDLResp<GpuStockData> resp = get("/dev/machine/gpu_stock",
                new ParameterizedTypeReference<AutoDLResp<GpuStockData>>() {
                });
        return resp.getData();
    }

    /**
     * Generic GET method
     */
    public <T> AutoDLResp<T> get(String path, ParameterizedTypeReference<AutoDLResp<T>> typeReference) {
        String url = autoDLProperties.getUrl() + path;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", autoDLProperties.getToken());
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ResponseEntity<AutoDLResp<T>> response = restTemplate.exchange(url, HttpMethod.GET, entity, typeReference);
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

    /**
     * Create a new deployment
     */
    public CreateDeploymentData createDeployment(CreateDeploymentReq req) {
        AutoDLResp<CreateDeploymentData> resp = post("/dev/deployment", req,
                new ParameterizedTypeReference<>() {
                });
        return resp.getData();
    }

    /**
     * Get deployment list
     */
    public DeploymentListData getDeploymentList(DeploymentListReq req) {
        AutoDLResp<DeploymentListData> resp = post("/dev/deployment/list", req,
                new ParameterizedTypeReference<AutoDLResp<DeploymentListData>>() {
                });
        return resp.getData();
    }

    /**
     * Get private image list
     */
    public com.autodl_backend.autodl.dto.image.PrivateImageListData getPrivateImageList(
            com.autodl_backend.autodl.dto.image.PrivateImageListReq req) {
        AutoDLResp<com.autodl_backend.autodl.dto.image.PrivateImageListData> resp = post("/dev/image/private/list", req,
                new ParameterizedTypeReference<>() {
                });
        return resp.getData();
    }
}
