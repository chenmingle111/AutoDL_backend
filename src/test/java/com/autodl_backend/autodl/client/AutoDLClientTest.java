
package com.autodl_backend.autodl.client;

import com.autodl_backend.autodl.config.AutoDLProperties;
import com.autodl_backend.autodl.dto.*;
import com.autodl_backend.autodl.dto.container.ContainerEventsReq;
import com.autodl_backend.autodl.dto.container.ContainerListReq;
import com.autodl_backend.autodl.dto.container.ContainerStopReq;
import com.autodl_backend.autodl.dto.deployment.BlacklistReq;
import com.autodl_backend.autodl.dto.deployment.CreateDeploymentReq;
import com.autodl_backend.autodl.dto.deployment.DeploymentListReq;
import com.autodl_backend.autodl.dto.image.PrivateImageListReq;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
    "autodl.api.url=https://api.autodl.com/api/v1",
    "autodl.api.token=eyJhbGciOiJFUzI1NiIsInR5cCI" +
            "6IkpXVCJ9.eyJ1aWQiOjM3Njg4LCJ1dWlkIj" +
            "oiOWU2YTU2Y2ItYWUzYi00NjViLWFkZDAtYW" +
            "ZmNjJiMDJkYzAyIiwiaXNfYWRtaW4iOmZhbH" +
            "NlLCJiYWNrc3RhZ2Vfcm9sZSI6IiIsImlzX3" +
            "N1cGVyX2FkbWluIjpmYWxzZSwic3ViX25hbW" +
            "UiOiIiLCJ0ZW5hbnQiOiJhdXRvZGwiLCJ1cG" +
            "siOiIifQ.skyJ7eSSrXbb0ye_gcN3krDzoNg" +
            "oemc0W4qODEg0f7fEqrfYvPdGhQTdP353kPH" +
            "f44u-6HsCNBYP17nm1fajxQ"
})
@Slf4j
public class AutoDLClientTest {

    @Autowired
    private AutoDLClient autoDLClient;

    @Autowired
    private AutoDLProperties autoDLProperties;

    @BeforeEach
    void setUp() {
        assertNotNull(autoDLClient);
        assertNotNull(autoDLProperties);
        assertNotNull(autoDLProperties.getToken());
    }

    @Test
    void testCreateInstance() {
        AutoDLCreateReq req = new AutoDLCreateReq();
        // 设置必要的请求参数
        req.setRegion("region-1");
        req.setGpuType("RTX 3090");
        req.setGpuNum(1);

        try {
            AutoDLResp<Object> response = autoDLClient.createInstance(req);
            assertNotNull(response);
            assertEquals("Success", response.getCode());
        } catch (Exception e) {
            // 在实际测试中可能需要根据API文档设置正确的参数
            fail("创建实例测试失败: " + e.getMessage());
        }
    }

    @Test
    void testGetInstanceStatus() {
        String instanceId = "test-instance-id"; // 替换为实际的实例ID

        try {
            AutoDLResp<Object> response = autoDLClient.getInstanceStatus(instanceId);
            assertNotNull(response);
            assertEquals("Success", response.getCode());
        } catch (Exception e) {
            // 在实际测试中可能需要根据API文档设置正确的参数
            fail("获取实例状态测试失败: " + e.getMessage());
        }
    }

    @Test
    void testGetContainerEvents() {
        ContainerEventsReq req = new ContainerEventsReq();
        // 设置必要的请求参数
        req.setDeploymentUuid("9c12c1bb2c");
        req.setPageIndex(1);
        req.setPageSize(10);

        try {
            var response = autoDLClient.getContainerEvents(req);
            assertNotNull(response);
        } catch (Exception e) {
            // 在实际测试中可能需要根据API文档设置正确的参数
            fail("获取容器事件测试失败: " + e.getMessage());
        }
    }

    @Test
    void testGetContainerList() {
        ContainerListReq req = new ContainerListReq();
        // 设置必要的请求参数
        req.setDeploymentUuid("test-deployment-uuid");
        req.setPageIndex(1);
        req.setPageSize(10);

        try {
            var response = autoDLClient.getContainerList(req);
            assertNotNull(response);
        } catch (Exception e) {
            // 在实际测试中可能需要根据API文档设置正确的参数
            fail("获取容器列表测试失败: " + e.getMessage());
        }
    }

    @Test
    void testStopContainer() {
        ContainerStopReq req = new ContainerStopReq();
        // 设置必要的请求参数
        req.setDeploymentContainerUuid("test-container-uuid");
        req.setDecreaseOneReplicaNum(false);

        try {
            var response = autoDLClient.stopContainer(req);
            assertNotNull(response);
        } catch (Exception e) {
            // 在实际测试中可能需要根据API文档设置正确的参数
            fail("停止容器测试失败: " + e.getMessage());
        }
    }

    @Test
    void testSetBlacklist() {
        BlacklistReq req = new BlacklistReq();
        // 设置必要的请求参数
        req.setDeploymentContainerUuid("test-container-uuid");
        req.setComment("测试黑名单设置");

        try {
            var response = autoDLClient.setBlacklist(req);
            assertNotNull(response);
        } catch (Exception e) {
            // 在实际测试中可能需要根据API文档设置正确的参数
            fail("设置黑名单测试失败: " + e.getMessage());
        }
    }

    @Test
    void testGetGpuStock() {
        try {
            var response = autoDLClient.getGpuStock();
            assertNotNull(response);
        } catch (Exception e) {
            fail("获取GPU库存测试失败: " + e.getMessage());
        }
    }

    @Test
    void testCreateDeployment() {
        CreateDeploymentReq req = new CreateDeploymentReq();
        // 设置必要的请求参数
        req.setName("api自动创建");
        req.setDeploymentType("ReplicaSet");
        req.setReplicaNum(2);
        req.setReuseContainer(true);
        
        // 创建容器模板
        var containerTemplate = com.autodl_backend.autodl.dto.deployment.ContainerTemplate.builder()
                .dcList(java.util.Arrays.asList("westDC2", "westDC3"))
                .gpuNameSet(java.util.Arrays.asList("RTX 4090"))
                .cudaVFrom(113)
                .cudaVTo(128)
                .gpuNum(1)
                .cpuNumFrom(1)
                .cpuNumTo(100)
                .memorySizeFrom(1)
                .memorySizeTo(256)
                .cmd("sleep 100")
                .priceFrom(100)
                .priceTo(9000)
                .imageUuid("image-d8ccae7a70")
                .build();
        
        req.setContainerTemplate(containerTemplate);

        try {
            var response = autoDLClient.createDeployment(req);
            assertNotNull(response);
        } catch (Exception e) {
            // 在实际测试中可能需要根据API文档设置正确的参数
            fail("创建部署测试失败: " + e.getMessage());
        }
    }

    @Test
    void testGetDeploymentList() {
        DeploymentListReq req = new DeploymentListReq();
        // 设置必要的请求参数
        req.setPageIndex(1);
        req.setPageSize(10);

        try {
            var response = autoDLClient.getDeploymentList(req);
            assertNotNull(response);
        } catch (Exception e) {
            // 在实际测试中可能需要根据API文档设置正确的参数
            fail("获取部署列表测试失败: " + e.getMessage());
        }
    }

    @Test
    void testGetPrivateImageList() {
        PrivateImageListReq req = new PrivateImageListReq();
        // 设置必要的请求参数
        req.setPageIndex(1);
        req.setPageSize(10);
        req.setOffset(0);

        try {
            var response = autoDLClient.getPrivateImageList(req);
            log.debug("获取私有镜像列表测试成功: {}", response);
            assertNotNull(response);
        } catch (Exception e) {
            // 在实际测试中可能需要根据API文档设置正确的参数
            fail("获取私有镜像列表测试失败: " + e.getMessage());
        }
    }
}
