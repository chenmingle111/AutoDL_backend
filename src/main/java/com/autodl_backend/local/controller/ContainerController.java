package com.autodl_backend.local.controller;

import com.autodl_backend.autodl.dto.container.ContainerEventData;
import com.autodl_backend.autodl.dto.container.ContainerEventsReq;
import com.autodl_backend.autodl.dto.container.ContainerListData;
import com.autodl_backend.autodl.dto.container.ContainerListReq;
import com.autodl_backend.autodl.dto.container.ContainerStopReq;
import com.autodl_backend.local.pojo.response.ApiResponse;
import com.autodl_backend.local.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Container related operations.
 */
@RestController
@RequestMapping("/api/containers")
public class ContainerController {

    @Autowired
    private ContainerService containerService;

    /**
     * Get container events.
     *
     * @param req Request DTO for querying container events.
     * @return Result containing container event data.
     */
    @PostMapping("/events")
    public ApiResponse<ContainerEventData> getContainerEvents(@RequestBody ContainerEventsReq req) {
        return ApiResponse.success(containerService.getContainerEvents(req));
    }

    /**
     * Get container list.
     *
     * @param req Request DTO for querying container list.
     * @return Result containing container list data.
     */
    @PostMapping("/list")
    public ApiResponse<ContainerListData> getContainerList(@RequestBody ContainerListReq req) {
        return ApiResponse.success(containerService.getContainerList(req));
    }

    /**
     * Stop a container.
     *
     * @param req Request DTO for stopping a container.
     * @return Result indicating success.
     */
    @PostMapping("/stop")
    public ApiResponse<Object> stopContainer(@RequestBody ContainerStopReq req) {
        return ApiResponse.success(containerService.stopContainer(req));
    }
}
