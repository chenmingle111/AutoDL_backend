package com.autodl_backend.local.controller;

import com.autodl_backend.autodl.dto.container.ContainerEventsReq;
import com.autodl_backend.autodl.dto.container.ContainerListReq;
import com.autodl_backend.autodl.dto.container.ContainerStopReq;
import com.autodl_backend.autodl.dto.deployment.*;
import com.autodl_backend.autodl.dto.image.PrivateImageListReq;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class LocalControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // Shared state between tests
    private static String imageUuid;
    private static String deploymentUuid;
    private static String deploymentContainerUuid;

    @Test
    @Order(2)
    public void testListPrivateImages() throws Exception {
        log.info("Step 2: List Private Images");
        PrivateImageListReq req = new PrivateImageListReq();
        req.setPageIndex(1);
        req.setPageSize(10);
        req.setOffset(0);

        MvcResult result = mockMvc.perform(post("/api/images/private/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("Success"))
                .andExpect(jsonPath("$.data.list").isArray())
                .andExpect(jsonPath("$.data.list[0].image_uuid").exists())
                .andReturn();

        String responseString = result.getResponse().getContentAsString();
        JsonNode rootNode = objectMapper.readTree(responseString);
        JsonNode listNode = rootNode.path("data").path("list");
        if (listNode.isArray() && listNode.size() > 0) {
            imageUuid = listNode.get(0).path("image_uuid").asText();
            log.info("Found imageUuid: {}", imageUuid);
        } else {
            // Fallback if no private images found, use a default image UUID
            log.warn("No private images found. Using a default image UUID for subsequent tests.");
            imageUuid = "default-image-uuid-for-testing";
            log.info("Using default imageUuid: {}", imageUuid);
        }
    }

//    @Test
//    @Order(3)
//    public void testCreateDeployment() throws Exception {
//        log.info("Step 3: Create Deployment using imageUuid: {}", imageUuid);
//        assertNotNull(imageUuid, "imageUuid should not be null. Check if testListPrivateImages found any images.");
//
//        CreateDeploymentReq req = new CreateDeploymentReq();
//        req.setName("api-test-deployment");
//        req.setDeploymentType("ReplicaSet");
//        req.setReplicaNum(1); // Start with 1 replica
//        req.setReuseContainer(true);
//
//        // Create container template
//        ContainerTemplate containerTemplate = ContainerTemplate.builder()
//                .dcList(java.util.Arrays.asList("westDC2", "westDC3"))
//                .gpuNameSet(List.of("RTX 4090")) // Adjust based on availability or make dynamic if needed
//                .cudaVFrom(113)
//                .cudaVTo(128)
//                .gpuNum(1)
//                .cpuNumFrom(1)
//                .cpuNumTo(100)
//                .memorySizeFrom(1)
//                .memorySizeTo(256)
//                .cmd("sleep 3600") // Long sleep to keep it running
//                .priceFrom(100)
//                .priceTo(9000)
//                .imageUuid(imageUuid)
//                .build();
//
//        req.setContainerTemplate(containerTemplate);
//
//        MvcResult result = mockMvc.perform(post("/api/deployments/create")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(req)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value("Success"))
//                .andExpect(jsonPath("$.data.deployment_uuid").exists())
//                .andReturn();
//
//        String responseString = result.getResponse().getContentAsString();
//        JsonNode rootNode = objectMapper.readTree(responseString);
//        deploymentUuid = rootNode.path("data").path("deployment_uuid").asText();
//        log.info("Created deploymentUuid: {}", deploymentUuid);
//    }
//
//    @Test
//    @Order(4)
//    public void testGetDeploymentList() throws Exception {
//        log.info("Step 4: Get Deployment List");
//        DeploymentListReq req = new DeploymentListReq();
//        req.setPageIndex(1);
//        req.setPageSize(10);
//
//        mockMvc.perform(post("/api/deployments/list")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(req)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value("Success"))
//                .andExpect(jsonPath("$.data.list").isArray());
//    }
//
//    @Test
//    @Order(5)
//    public void testSetReplicas() throws Exception {
//        log.info("Step 5: Set Replicas for deploymentUuid: {}", deploymentUuid);
//        assertNotNull(deploymentUuid, "deploymentUuid should not be null.");
//
//        ReplicaNumReq req = new ReplicaNumReq();
//        req.setDeploymentUuid(deploymentUuid);
//        req.setReplicaNum(2); // Increase to 2
//
//        mockMvc.perform(put("/api/deployments/setReplicas")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(req)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value("Success"));
//    }
//
//    @Test
//    @Order(6)
//    public void testGetContainerList() throws Exception {
//        log.info("Step 6: Get Container List for deploymentUuid: {}", deploymentUuid);
//        assertNotNull(deploymentUuid, "deploymentUuid should not be null.");
//
//        // Wait a bit for containers to be created/scheduled if necessary
//        // In a real integration test against a real backend, this might need a retry
//        // loop
//        // For now, we assume the API returns the list even if empty or pending
//
//        ContainerListReq req = new ContainerListReq();
//        req.setDeploymentUuid(deploymentUuid);
//        req.setPageIndex(1);
//        req.setPageSize(10);
//
//        MvcResult result = mockMvc.perform(post("/api/containers/list")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(req)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value("Success"))
//                .andReturn();
//
//        String responseString = result.getResponse().getContentAsString();
//        JsonNode rootNode = objectMapper.readTree(responseString);
//        JsonNode listNode = rootNode.path("data").path("list");
//        if (listNode.isArray() && listNode.size() > 0) {
//            // Find a container uuid. The field name might be 'instance_uuid' or 'uuid'
//            // depending on DTO
//            // Let's check the DTO or assume a common name.
//            // Looking at ContainerController, it returns ContainerListData.
//            // I'll assume the field is 'uuid' or 'instance_uuid'.
//            // Based on typical AutoDL API, it might be 'uuid'.
//            // Let's try to get 'uuid' or 'instance_uuid'.
//            JsonNode firstContainer = listNode.get(0);
//            if (firstContainer.has("uuid")) {
//                deploymentContainerUuid = firstContainer.get("uuid").asText();
//            } else if (firstContainer.has("instance_uuid")) {
//                deploymentContainerUuid = firstContainer.get("instance_uuid").asText();
//            }
//            log.info("Found deploymentContainerUuid: {}", deploymentContainerUuid);
//        } else {
//            log.warn("No containers found for deployment yet.");
//        }
//    }

//    @Test
//    @Order(7)
//    public void testGetContainerEvents() throws Exception {
//        log.info("Step 7: Get Container Events for deploymentUuid: {}", deploymentUuid);
//        assertNotNull(deploymentUuid, "deploymentUuid should not be null.");
//
//        ContainerEventsReq req = new ContainerEventsReq();
//        req.setDeploymentUuid(deploymentUuid);
//        req.setPageIndex(1);
//        req.setPageSize(10);
//
//        mockMvc.perform(post("/api/containers/events")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(req)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value("Success"));
//    }
//
//    @Test
//    @Order(8)
//    public void testStopContainer() throws Exception {
//        log.info("Step 8: Stop Container for deploymentContainerUuid: {}", deploymentContainerUuid);
//        // If no container was found, skip or fail.
//        if (deploymentContainerUuid == null) {
//            log.warn("Skipping testStopContainer because deploymentContainerUuid is null.");
//            return;
//        }
//
//        ContainerStopReq req = new ContainerStopReq();
//        req.setDeploymentContainerUuid(deploymentContainerUuid);
//        req.setDecreaseOneReplicaNum(false);
//
//        mockMvc.perform(post("/api/containers/stop")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(req)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value("Success"));
//    }
//
//    @Test
//    @Order(9)
//    public void testSetBlacklist() throws   Exception {
//        log.info("Step 9: Set Blacklist for deploymentContainerUuid: {}", deploymentContainerUuid);
//        if (deploymentContainerUuid == null) {
//            log.warn("Skipping testSetBlacklist because deploymentContainerUuid is null.");
//            return;
//        }
//
//        BlacklistReq req = new BlacklistReq();
//        req.setDeploymentContainerUuid(deploymentContainerUuid);
//        req.setComment("Integration Test Blacklist");
//
//        mockMvc.perform(post("/api/management/blacklist")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(req)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value("Success"));
//    }
//
//    @Test
//    @Order(10)
//    public void testStopDeployment() throws Exception {
//        log.info("Step 10: Stop Deployment for deploymentUuid: {}", deploymentUuid);
//        assertNotNull(deploymentUuid, "deploymentUuid should not be null.");
//
//        StopDeploymentReq req = new StopDeploymentReq();
//        req.setDeploymentUuid(deploymentUuid);
//        req.setOperate("stop");
//
//        mockMvc.perform(put("/api/deployments/stop")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(req)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value("Success"));
//    }
//
//    @Test
//    @Order(11)
//    public void testDeleteDeployment() throws Exception {
//        log.info("Step 11: Delete Deployment for deploymentUuid: {}", deploymentUuid);
//        assertNotNull(deploymentUuid, "deploymentUuid should not be null.");
//
//        DeploymentDeleteReq req = new DeploymentDeleteReq();
//        req.setDeploymentUuid(deploymentUuid);
//
//        mockMvc.perform(delete("/api/deployments/delete")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(req)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.code").value("Success"));
//    }
}
