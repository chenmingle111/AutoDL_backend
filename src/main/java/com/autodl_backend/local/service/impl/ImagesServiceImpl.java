package com.autodl_backend.local.service.impl;

import com.autodl_backend.local.mapper.ImagesMapper;
import com.autodl_backend.local.pojo.entity.Images;
import com.autodl_backend.local.service.ImagesService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Images表的服务层实现类
 */
@Slf4j
@Service
public class ImagesServiceImpl extends ServiceImpl<ImagesMapper, Images> implements ImagesService {

    @org.springframework.beans.factory.annotation.Autowired
    private com.autodl_backend.autodl.client.AutoDLClient autoDLClient;

    @Override
    public com.autodl_backend.autodl.dto.image.PrivateImageListData getPrivateImages(int pageIndex, int pageSize) {
        com.autodl_backend.autodl.dto.image.PrivateImageListReq req = new com.autodl_backend.autodl.dto.image.PrivateImageListReq();
        req.setPageIndex(pageIndex);
        req.setPageSize(pageSize);
        req.setOffset(0); // Default offset

        com.autodl_backend.autodl.dto.AutoDLResp<com.autodl_backend.autodl.dto.image.PrivateImageListData> resp = autoDLClient
                .post(
                        "/dev/image/private/list",
                        req,
                        new org.springframework.core.ParameterizedTypeReference<com.autodl_backend.autodl.dto.AutoDLResp<com.autodl_backend.autodl.dto.image.PrivateImageListData>>() {
                        });

        if (resp == null || !"Success".equals(resp.getCode())) {
            throw new RuntimeException(
                    "Failed to get private images: " + (resp != null ? resp.getMsg() : "Unknown error"));
        }

        return resp.getData();
    }
}
