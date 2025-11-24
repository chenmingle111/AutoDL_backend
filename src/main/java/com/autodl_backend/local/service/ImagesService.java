package com.autodl_backend.local.service;

import com.autodl_backend.autodl.dto.image.PrivateImageListData;
import com.autodl_backend.local.exception.ImageNotFoundException;
import com.autodl_backend.local.exception.ImagePullFailedException;
import com.autodl_backend.local.exception.ImagePermissionDeniedException;
import com.autodl_backend.local.exception.UnsupportedImageFormatException;
import com.autodl_backend.local.pojo.entity.Images;
import com.autodl_backend.local.pojo.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * Images表的服务层接口
 */
public interface ImagesService extends IService<Images> {

    /**
     * 获取私有镜像列表
     */
    PrivateImageListData getPrivateImages(int pageIndex, int pageSize);

    /**
     * 根据UUID获取镜像
     * 
     * @param imageUuid 镜像UUID
     * @return 镜像对象
     * @throws ImageNotFoundException 如果镜像不存在
     */
    Images getImageByUuid(String imageUuid) throws ImageNotFoundException;

    /**
     * 检查用户是否有权访问镜像
     * 
     * @param user 用户对象
     * @param imageUuid 镜像UUID
     * @return 镜像对象
     * @throws ImageNotFoundException 如果镜像不存在
     * @throws ImagePermissionDeniedException 如果用户无权访问镜像
     */
    Images checkImagePermission(Users user, String imageUuid) 
        throws ImageNotFoundException, ImagePermissionDeniedException;

    /**
     * 检查镜像状态是否可用
     * 
     * @param imageUuid 镜像UUID
     * @throws ImageNotFoundException 如果镜像不存在
     * @throws ImagePullFailedException 如果镜像状态不可用
     */
    void checkImageStatus(String imageUuid) 
        throws ImageNotFoundException, ImagePullFailedException;
}
