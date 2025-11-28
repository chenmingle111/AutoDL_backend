
package com.autodl_backend.autodl.client;

import com.autodl_backend.autodl.config.AutoDLProperties;
import com.autodl_backend.autodl.dto.*;
import com.autodl_backend.autodl.dto.container.ContainerEventsReq;
import com.autodl_backend.autodl.dto.container.ContainerListReq;
import com.autodl_backend.autodl.dto.container.ContainerStopReq;
import com.autodl_backend.autodl.dto.deployment.*;
import com.autodl_backend.autodl.dto.image.PrivateImageListReq;
import com.autodl_backend.autodl.dto.machines.GpuStockReq;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

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
/*
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
*/

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
        req.setDeploymentContainerUuid("98fc43be4e-795c41d8");
        req.setDecreaseOneReplicaNum(false);

        try {
            autoDLClient.stopContainer(req);
        } catch (Exception e) {
            // 在实际测试中可能需要根据API文档设置正确的参数
            fail("停止容器测试失败: " + e.getMessage());
        }
    }

    @Test
    void testStopDeployment() {
        StopDeploymentReq req = new StopDeploymentReq();

        req.setDeploymentUuid("2c75c3585d");
        req.setOperate("stop");

        try {
            autoDLClient.stopDeployment(req);
        } catch (Exception e) {
            // 在实际测试中可能需要根据API文档设置正确的参数
            fail("停止部署测试失败: " + e.getMessage());
        }
    }

    @Test
    void testSetBlacklist() {
        BlacklistReq req = new BlacklistReq();
        // 设置必要的请求参数
        req.setDeploymentContainerUuid("9c12c1bb2c-bd79408a8f-53ce5214f4-8404088b5");
        req.setComment("测试黑名单设置");

        try {
            autoDLClient.setBlacklist(req);
        } catch (Exception e) {
            // 在实际测试中可能需要根据API文档设置正确的参数
            fail("设置黑名单测试失败: " + e.getMessage());
        }
    }

    @Test
    void testGetGpuStock() {
        try {
            GpuStockReq req = new GpuStockReq();

            req.setRegionSign("westDC2");

            var response = autoDLClient.getGpuStock(req);
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
                .gpuNameSet(List.of("RTX 4090"))
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

    @Test
    void testSetReplicaNum() {
        ReplicaNumReq req = new ReplicaNumReq();
        // 设置必要的请求参数
        req.setDeploymentUuid("2c75c3585d");
        req.setReplicaNum(10);

        try {
            autoDLClient.setReplicaNum(req);
        } catch (Exception e) {
            fail("设置副本数量测试失败: " + e.getMessage());
        }
    }

    @Test
    void testDeleteDeployment() {
        DeploymentDeleteReq req = new DeploymentDeleteReq();
        // 设置必要的请求参数
        req.setDeploymentUuid("2c75c3585d");

        try {
            autoDLClient.deleteDeployment(req);
        } catch (Exception e) {
            fail("删除部署测试失败: " + e.getMessage());
        }
    }
}
