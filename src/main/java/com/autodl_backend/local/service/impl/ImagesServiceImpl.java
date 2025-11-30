package com.autodl_backend.local.service.impl;

import com.autodl_backend.autodl.client.AutoDLClient;
import com.autodl_backend.autodl.dto.image.PrivateImageItem;
import com.autodl_backend.autodl.dto.image.PrivateImageListData;
import com.autodl_backend.autodl.dto.image.PrivateImageListReq;
import com.autodl_backend.local.mapper.ImagesMapper;
import com.autodl_backend.local.pojo.entity.Images;
import com.autodl_backend.local.service.ImagesService;
import com.autodl_backend.util.AutodlFataConverter.ImageConverter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * Images表的服务层实现类
 */
@Slf4j
@Service
public class ImagesServiceImpl extends ServiceImpl<ImagesMapper, Images> implements ImagesService {

    @Autowired
    private AutoDLClient autoDLClient;


    @Override
    public PrivateImageListData getPrivateImages(PrivateImageListReq req) {
        // 从请求中获取token,在调用工具类获取uid
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String uid = (String) request.getAttribute("uid");

        // 记录用户操作日志
        log.info("用户[{}]正在获取私有镜像列表", uid);

        // 调用AutoDL客户端获取私有镜像列表
        PrivateImageListData privateImageList = autoDLClient.getPrivateImageList(req);

        // 如果获取到数据，将其转换为Images实体并保存到数据库
        if (privateImageList != null && privateImageList.getList() != null) {
            for (PrivateImageItem privateImageItem : privateImageList.getList()) {
                // 先查询是否已存在该imageUuid的记录
                Images existingImage = getOne(
                        new QueryWrapper<Images>()
                                .eq("image_uuid", privateImageItem.getImageUuid())
                );

                if (existingImage == null) {
                    // 不存在，则转换并保存
                    Images image = ImageConverter.convertToImage(privateImageItem, uid);
                    save(image);
                }
                // 已存在则跳过，不做任何操作
            }
        }

        return privateImageList;
    }
}
