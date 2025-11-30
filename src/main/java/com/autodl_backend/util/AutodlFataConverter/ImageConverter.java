package com.autodl_backend.util.AutodlFataConverter;

import com.autodl_backend.autodl.dto.image.PrivateImageItem;
import com.autodl_backend.local.pojo.entity.Images;
import com.autodl_backend.local.pojo.enums.ImageStatus;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * AutoDL数据转换工具类
 * 用于将AutoDL API返回的DTO对象转换为本地实体对象
 */
@Slf4j
public class ImageConverter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 将PrivateImageItem转换为Images实体
     * @param item AutoDL返回的镜像项
     * @param userId 用户ID
     * @return Images实体
     */
    public static Images convertToImage(PrivateImageItem item, String userId) {
        // 创建新的Images实体对象
        Images image = new Images();

        // 设置基本信息
        image.setUid(userId);
        image.setId(item.getId());
        image.setImageUuid(item.getImageUuid());
        image.setImageName(item.getImageName());

        // 转换状态
        if (item.getStatus() != null) {
            try {
                image.setStatus(ImageStatus.valueOf(item.getStatus().toUpperCase()));
            } catch (IllegalArgumentException e) {
                log.warn("Unknown image status: {}, using default", item.getStatus());
                image.setStatus(ImageStatus.CREATING);
            }
        }

        // 转换时间
        if (item.getCreatedAt() != null) {
            try {
                image.setCreatedAt(LocalDateTime.parse(item.getCreatedAt(), DATE_FORMATTER));
            } catch (Exception e) {
                log.warn("Failed to parse created_at: {}", item.getCreatedAt());
                image.setCreatedAt(LocalDateTime.now());
            }
        }

        if (item.getUpdatedAt() != null) {
            try {
                image.setUpdatedAt(LocalDateTime.parse(item.getUpdatedAt(), DATE_FORMATTER));
            } catch (Exception e) {
                log.warn("Failed to parse updated_at: {}", item.getUpdatedAt());
                image.setUpdatedAt(LocalDateTime.now());
            }
        }

        return image;
    }


}
