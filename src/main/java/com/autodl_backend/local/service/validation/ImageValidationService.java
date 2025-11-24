package com.autodl_backend.local.service.validation;

import com.autodl_backend.local.exception.ImageNotFoundException;
import com.autodl_backend.local.exception.ImagePullFailedException;
import com.autodl_backend.local.exception.ImagePermissionDeniedException;
import com.autodl_backend.local.exception.UnsupportedImageFormatException;
import com.autodl_backend.local.mapper.ImagesMapper;
import com.autodl_backend.local.pojo.entity.Images;
import com.autodl_backend.local.pojo.entity.Users;
import com.autodl_backend.local.pojo.enums.ImageStatus;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 镜像验证服务
 * 集中处理所有镜像相关的验证逻辑
 */
@Service
public class ImageValidationService {

    // 支持的镜像格式列表
    private static final List<String> SUPPORTED_IMAGE_FORMATS = Arrays.asList(
        "docker", "oci", "appc"
    );

    @Autowired
    private ImagesMapper imagesMapper;

    /**
     * 验证镜像是否存在
     *
     * @param imageUuid 镜像UUID
     * @return 镜像对象
     * @throws ImageNotFoundException 如果镜像不存在
     */
    public Images validateImageExists(String imageUuid) throws ImageNotFoundException {
        if (!StringUtils.hasText(imageUuid)) {
            throw new ImageNotFoundException("镜像UUID不能为空");
        }

        QueryWrapper<Images> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("image_uuid", imageUuid)
                   .eq("is_deleted", 0);

        Images image = imagesMapper.selectOne(queryWrapper);
        if (image == null) {
            throw new ImageNotFoundException(imageUuid);
        }

        return image;
    }

    /**
     * 验证镜像状态是否可用
     *
     * @param imageUuid 镜像UUID
     * @return 镜像对象
     * @throws ImageNotFoundException 如果镜像不存在
     * @throws ImagePullFailedException 如果镜像状态不可用
     */
    public Images validateImageStatus(String imageUuid) throws ImageNotFoundException, ImagePullFailedException {
        Images image = validateImageExists(imageUuid);

        if (image.getStatus() == ImageStatus.ERROR) {
            throw new ImagePullFailedException(imageUuid, "镜像状态为错误");
        }

        return image;
    }

    /**
     * 验证镜像格式是否支持
     *
     * @param imageUuid 镜像UUID
     * @param format 镜像格式
     * @throws UnsupportedImageFormatException 如果镜像格式不支持
     */
    public void validateImageFormat(String imageUuid, String format) throws UnsupportedImageFormatException {
        if (format == null || format.trim().isEmpty()) {
            throw new UnsupportedImageFormatException(imageUuid, "空格式");
        }

        if (!SUPPORTED_IMAGE_FORMATS.contains(format.toLowerCase())) {
            throw new UnsupportedImageFormatException(imageUuid, format);
        }
    }

    /**
     * 验证用户是否有权访问镜像
     *
     * @param user 用户对象
     * @param imageUuid 镜像UUID
     * @return 镜像对象
     * @throws ImageNotFoundException 如果镜像不存在
     * @throws ImagePermissionDeniedException 如果用户无权访问镜像
     */
    public Images validateImagePermission(Users user, String imageUuid) 
            throws ImageNotFoundException, ImagePermissionDeniedException {
        if (user == null) {
            throw new ImagePermissionDeniedException("用户对象不能为空");
        }

        Images image = validateImageExists(imageUuid);

        // 检查用户是否是镜像的所有者或管理员
        if (!user.getUid().equals(image.getUid()) &&
            !user.getRole().name().equals("ADMIN")) {
            throw new ImagePermissionDeniedException(user.getUid(), imageUuid);
        }

        return image;
    }
}
