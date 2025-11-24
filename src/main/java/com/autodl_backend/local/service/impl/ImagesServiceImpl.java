package com.autodl_backend.local.service.impl;

import com.autodl_backend.autodl.client.AutoDLClient;
import com.autodl_backend.autodl.dto.image.PrivateImageListData;
import com.autodl_backend.autodl.dto.image.PrivateImageListReq;
import com.autodl_backend.local.exception.ImageNotFoundException;
import com.autodl_backend.local.exception.ImagePermissionDeniedException;
import com.autodl_backend.local.exception.ImagePullFailedException;
import com.autodl_backend.local.mapper.ImagesMapper;
import com.autodl_backend.local.pojo.entity.Images;
import com.autodl_backend.local.pojo.entity.Users;
import com.autodl_backend.local.service.ImagesService;
import com.autodl_backend.local.service.validation.ImageValidationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Images表的服务层实现类
 */
@Slf4j
@Service
public class ImagesServiceImpl extends ServiceImpl<ImagesMapper, Images> implements ImagesService {

    @Autowired
    private AutoDLClient autoDLClient;

    @Autowired
    private ImageValidationService validationService;

    @Override
    public PrivateImageListData getPrivateImages(int pageIndex, int pageSize) {
       PrivateImageListReq req = new com.autodl_backend.autodl.dto.image.PrivateImageListReq();
        req.setPageIndex(pageIndex);
        req.setPageSize(pageSize);
        req.setOffset(0); // Default offset

        return autoDLClient.getPrivateImageList(req);
    }

    @Override
    public Images getImageByUuid(String imageUuid) throws ImageNotFoundException {
        return validationService.validateImageExists(imageUuid);
    }

    @Override
    public Images checkImagePermission(Users user, String imageUuid)
            throws ImageNotFoundException, ImagePermissionDeniedException {
        return validationService.validateImagePermission(user, imageUuid);
    }

    @Override
    public void checkImageStatus(String imageUuid)
            throws ImageNotFoundException, ImagePullFailedException {
        validationService.validateImageStatus(imageUuid);
    }
}
