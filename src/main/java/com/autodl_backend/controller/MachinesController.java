package com.autodl_backend.controller;

import com.autodl_backend.pojo.Machines;
import com.autodl_backend.pojo.response.ApiResponse;
import com.autodl_backend.service.MachinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/dev/machine")
public class MachinesController {
    @Autowired
    private MachinesService machinesService;

    @GetMapping("/gpu_stock")
    public ApiResponse<HashMap<String, Machines>> getGpuStock() {
        HashMap<String, Machines> map  = new HashMap<>();
        map=machinesService.getGpuStock();
        return ApiResponse.success(map);
    }
}
