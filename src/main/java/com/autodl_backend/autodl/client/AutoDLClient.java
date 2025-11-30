package com.autodl_backend.autodl.client;

import com.autodl_backend.autodl.config.AutoDLProperties;
import com.autodl_backend.autodl.dto.*;
import com.autodl_backend.autodl.dto.container.ContainerEventsReq;
import com.autodl_backend.autodl.dto.container.ContainerListData;
import com.autodl_backend.autodl.dto.container.ContainerListReq;
import com.autodl_backend.autodl.dto.container.ContainerStopReq;
import com.autodl_backend.autodl.dto.container.ContainerEventData;
import com.autodl_backend.autodl.dto.deployment.*;
import com.autodl_backend.autodl.dto.image.PrivateImageListData;
import com.autodl_backend.autodl.dto.image.PrivateImageListReq;
import com.autodl_backend.autodl.dto.machines.*;
import com.autodl_backend.autodl.exception.AutoDLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

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
     * 发送DELETE请求到AutoDL API并处理响应
     */
    public <T> AutoDLResp<T> delete(String path, Object req, ParameterizedTypeReference<AutoDLResp<T>> typeReference) {
        // 构建完整的API URL
        String url = autoDLProperties.getUrl() + path;
        // 设置HTTP请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", autoDLProperties.getToken()); // 设置认证token
        headers.set("Content-Type", "application/json"); // 设置内容类型为JSON
        HttpEntity<Object> entity = new HttpEntity<>(req, headers); // 创建HTTP请求实体，包含请求体和请求头

        ResponseEntity<AutoDLResp<T>> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, typeReference); // 发送DELETE请求并获取响应
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
     * get请求
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
     * 获取容器事件
     */
    public ContainerEventData getContainerEvents(ContainerEventsReq req) {
        AutoDLResp<ContainerEventData> resp = post("/dev/deployment/container/event/list", req,
                new ParameterizedTypeReference<>() {
                });
        return resp.getData();
    }

    /**
     * 获取容器列表
     */
    public ContainerListData getContainerList(ContainerListReq req) {
        AutoDLResp<ContainerListData> resp = post("/dev/deployment/container/list", req,
                new ParameterizedTypeReference<>() {
                });
        return resp.getData();
    }

    /**
     * 停止容器
     */
    public Object stopContainer(ContainerStopReq req) {
        AutoDLResp<Object> resp = put("/dev/deployment/container/stop", req,
                new ParameterizedTypeReference<>() {
                });
        return resp.getData();
    }

    /**
     * 设置调度黑名单
     */
    public Object setBlacklist(SetBlacklistReq req) {
        AutoDLResp<Object> resp = post("/dev/deployment/blacklist", req,
                new ParameterizedTypeReference<>() {
                });
        return resp.getData();
    }

    /**
     * 获取GPU库存
     */
    public GpuStockData getGpuStock(GpuStockReq req) {
        // 直接接收数组格式的响应
        AutoDLResp<List<Map<GpuInfo, GpuStockInfo>>> resp = post("/dev/machine/region/gpu_stock", req,
                new ParameterizedTypeReference<>() {
                });
        // 将数组转换为GpuStockData对象
        return new GpuStockData(resp.getData());
    }

    /**
     * 创建部署
     */
    public CreateDeploymentData createDeployment(CreateDeploymentReq req) {
        AutoDLResp<CreateDeploymentData> resp = post("/dev/deployment", req,
                new ParameterizedTypeReference<>() {
                });
        return resp.getData();
    }

    /**
     * 获取部署列表
     */
    public DeploymentListData getDeploymentList(DeploymentListReq req) {
        AutoDLResp<DeploymentListData> resp = post("/dev/deployment/list", req,
                new ParameterizedTypeReference<>() {
                });
        return resp.getData();
    }

    /**
     * 获取私有镜像
     */
    public PrivateImageListData getPrivateImageList(PrivateImageListReq req) {
        AutoDLResp<PrivateImageListData> resp = post("/dev/image/private/list", req,
                new ParameterizedTypeReference<>() {
                });
        return resp.getData();
    }

    /**
     * 设置副本数量
     */
    public Object setReplicaNum(ReplicaNumReq req) {
        AutoDLResp<Object> resp = put("/dev/deployment/replica_num", req,
                new ParameterizedTypeReference<>() {
                });
        return resp.getData();
    }

    /**
     * 删除部署
     */
    public Object deleteDeployment(DeploymentDeleteReq req) {
        AutoDLResp<Object> resp = delete("/dev/deployment", req,
                new ParameterizedTypeReference<>() {
                });
        return resp.getData();
    }

    /**
     * 停止部署
     */
    public Object stopDeployment(StopDeploymentReq req) {
        AutoDLResp<Object> resp = put("/dev/deployment/operate", req,
                new ParameterizedTypeReference<>() {
                });
        return resp.getData();
    }
}
