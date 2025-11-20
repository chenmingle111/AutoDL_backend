package com.autodl_backend.controller;

import com.autodl_backend.DTO.ContainerDTO;
import com.autodl_backend.DTO.ContainerStopDTO;
import com.autodl_backend.pojo.Containers;
import com.autodl_backend.pojo.page.PageResponse;
import com.autodl_backend.pojo.response.ApiResponse;
import com.autodl_backend.service.ContainersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dev/deployment/container")
public class ContainerController {
    @Autowired
    private ContainersService containerService;

    /**
     * 获取容器细节
     */
    @PostMapping("/list")
    public ApiResponse<PageResponse<Containers>> listContainer(@RequestBody ContainerDTO containerDTO) {
         PageResponse<Containers> pageResponse= containerService.listContainer(containerDTO);
         return ApiResponse.success(pageResponse);
    }

    /**
     * 停止容器
     */
    @PutMapping("/stop")
    public ApiResponse stopContainer(@RequestBody ContainerStopDTO containerStopDTO) {
        containerService.stopContainer(containerStopDTO);
        return ApiResponse.success();
    }

}
