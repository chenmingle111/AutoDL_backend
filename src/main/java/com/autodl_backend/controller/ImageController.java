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

    // 私有镜像的操作
    /**
     * 获取私有镜像列表
     * 
     * <p>
     * 调用AutoDL私有云API获取用户的私有镜像列表
     * </p>
     * 
     * <h3>请求示例：</h3>
     * 
     * <pre>
     * POST /api/v1/dev/image/private/list
     * Headers: 
     *   Authorization: your_token_here
     * Body:
     * {
     *   "page_index": 1,
     *   "page_size": 10,
     *   "offset": 0
     * }
     * </pre>
     * 
     * <h3>响应示例：</h3>
     * 
     * <pre>
     * {
     *   "code": "Success",
     *   "msg": "",
     *   "data": {
     *     "list": [
     *       {
     *         "id": 111,
     *         "image_uuid": "image-db8346e037",
     *         "image_name": "My Custom Image",
     *         "status": "FINISHED",
     *         "created_at": "2022-01-20T18:34:08+08:00",
     *         "updated_at": "2022-01-20T18:34:08+08:00"
     *       }
     *     ],
     *     "page_index": 1,
     *     "page_size": 10,
     *     "max_page": 1,
     *     "offset": 0,
     *     "result_total": 1
     *   }
     * }
     * </pre>
     * 
     * @param pageRequest 分页请求参数
     * @return 包含镜像列表的分页响应
     */
    @PostMapping("/private/list")
    public ApiResponse<PageResponse<Images>> listPage(@RequestBody PageRequest pageRequest) {
        PageResponse<Images> pageResponse = imagesService.listPage(pageRequest);
        return ApiResponse.success(pageResponse);
    }
}
