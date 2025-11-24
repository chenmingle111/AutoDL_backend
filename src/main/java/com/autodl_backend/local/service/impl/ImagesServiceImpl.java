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
import com.autodl_backend.util.ImageValidationUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Images表的服务层实现类
 */
@Slf4j
@Service
public class ImagesServiceImpl extends ServiceImpl<ImagesMapper, Images> implements ImagesService {

    @Autowired
    private AutoDLClient autoDLClient;

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
        if (!StringUtils.hasText(imageUuid)) {
            throw new ImageNotFoundException("镜像UUID不能为空");
        }

        QueryWrapper<Images> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("image_uuid", imageUuid)
                   .eq("is_deleted", 0);

        Images image = getOne(queryWrapper);
        ImageValidationUtils.checkImageExists(image);

        return image;
    }

    @Override
    public Images checkImagePermission(Users user, String imageUuid) 
            throws ImageNotFoundException, ImagePermissionDeniedException {
        Images image = getImageByUuid(imageUuid);
        ImageValidationUtils.checkImagePermission(user, image);

        return image;
    }

    @Override
    public void checkImageStatus(String imageUuid) 
            throws ImageNotFoundException, ImagePullFailedException {
        Images image = getImageByUuid(imageUuid);
        ImageValidationUtils.checkImageStatus(image);
    }
}
