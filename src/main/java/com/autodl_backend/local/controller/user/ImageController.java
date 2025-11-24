package com.autodl_backend.local.controller.user;

import com.autodl_backend.autodl.dto.image.PrivateImageListData;
import com.autodl_backend.autodl.dto.image.PrivateImageListReq;
import com.autodl_backend.local.pojo.response.ApiResponse;
import com.autodl_backend.local.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Image related operations.
 */
@RestController
@RequestMapping("/api/user/images")
public class ImageController {

    @Autowired
    private ImagesService imagesService;

    /**
     * Get private image list.
     *
     * @param req Request DTO containing page index and page size.
     * @return Result containing private image list data.
     */
    @PostMapping("/private/list")
    public ApiResponse<PrivateImageListData> listPrivateImages(@RequestBody PrivateImageListReq req) {
        return ApiResponse.success(imagesService.getPrivateImages(req.getPageIndex(), req.getPageSize()));
    }
}
