package com.autodl_backend.util;

import com.autodl_backend.local.exception.ImageNotFoundException;
import com.autodl_backend.local.exception.ImagePullFailedException;
import com.autodl_backend.local.exception.ImagePermissionDeniedException;
import com.autodl_backend.local.exception.UnsupportedImageFormatException;
import com.autodl_backend.local.pojo.entity.Images;
import com.autodl_backend.local.pojo.entity.Users;
import com.autodl_backend.local.pojo.enums.ImageStatus;

import java.util.Arrays;
import java.util.List;

/**
 * 镜像验证工具类
 * 提供镜像相关的验证方法
 */
public class ImageValidationUtils {

    // 支持的镜像格式列表
    private static final List<String> SUPPORTED_IMAGE_FORMATS = Arrays.asList(
        "docker", "oci", "appc"
    );

    /**
     * 检查镜像是否存在
     */
    public static void checkImageExists(Images image) throws ImageNotFoundException {
        if (image == null) {
            throw new ImageNotFoundException("镜像对象不能为空");
        }

        if (image.getIsDeleted() == 1) {
            throw new ImageNotFoundException(image.getImageUuid());
        }
    }

    /**
     * 检查镜像状态是否可用
     */
    public static void checkImageStatus(Images image) throws ImagePullFailedException {
        if (image == null) {
            throw new ImagePullFailedException("镜像对象不能为空");
        }

        if (image.getStatus() == ImageStatus.ERROR) {
            throw new ImagePullFailedException(image.getImageUuid(), "镜像状态为错误");
        }
    }

    /**
     * 检查镜像格式是否支持
     */
    public static void checkImageFormat(String imageUuid, String format) throws UnsupportedImageFormatException {
        if (format == null || format.trim().isEmpty()) {
            throw new UnsupportedImageFormatException(imageUuid, "空格式");
        }

        if (!SUPPORTED_IMAGE_FORMATS.contains(format.toLowerCase())) {
            throw new UnsupportedImageFormatException(imageUuid, format);
        }
    }

    /**
     * 检查用户是否有权访问镜像
     */
    public static void checkImagePermission(Users user, Images image) throws ImagePermissionDeniedException {
        if (user == null) {
            throw new ImagePermissionDeniedException("用户对象不能为空");
        }

        if (image == null) {
            throw new ImagePermissionDeniedException("镜像对象不能为空");
        }

        // 检查用户是否是镜像的所有者或管理员
        if (!user.getUid().equals(image.getUid()) && 
            !user.getRole().name().equals("ADMIN")) {
            throw new ImagePermissionDeniedException(user.getUid(), image.getImageUuid());
        }
    }
}
