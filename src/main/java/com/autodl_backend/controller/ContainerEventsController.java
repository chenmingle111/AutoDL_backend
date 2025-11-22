package com.autodl_backend.controller;

import com.autodl_backend.integration.DTO.ContainerEventDTO;
import com.autodl_backend.pojo.ContainerEvents;
import com.autodl_backend.pojo.page.PageResponse;
import com.autodl_backend.pojo.response.ApiResponse;
import com.autodl_backend.service.ContainerEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dev/deployment/container")
public class ContainerEventsController {
    @Autowired
    ContainerEventsService containerEventsService;

    @PostMapping("/event")
    public ApiResponse<PageResponse<ContainerEvents>> listContainerEvents(ContainerEventDTO containerEventDTO) {
        PageResponse<ContainerEvents> pageResponse =containerEventsService.listContainerEvent(containerEventDTO);
        return ApiResponse.success(pageResponse);
    }
}
