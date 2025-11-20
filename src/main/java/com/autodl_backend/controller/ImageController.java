package com.autodl_backend.controller;

import com.autodl_backend.pojo.Images;
import com.autodl_backend.pojo.page.PageRequest;
import com.autodl_backend.pojo.page.PageResponse;
import com.autodl_backend.pojo.response.ApiResponse;
import com.autodl_backend.service.ImagesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/dev/image")
public class ImageController {

    @Autowired
    private ImagesService imagesService;

    //私有镜像的操作
    /**
     * 获取私有镜像列表
     */
    @PostMapping("/private/list")
    public ApiResponse<PageResponse<Images>> listPage (@RequestBody PageRequest pageRequest){
        PageResponse<Images> pageResponse = imagesService.listPage(pageRequest);
        return ApiResponse.success(pageResponse);
    }
}
