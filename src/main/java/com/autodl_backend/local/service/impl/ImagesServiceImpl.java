package com.autodl_backend.local.service.impl;

import com.autodl_backend.autodl.client.AutoDLClient;
import com.autodl_backend.autodl.dto.image.PrivateImageListData;
import com.autodl_backend.autodl.dto.image.PrivateImageListReq;
import com.autodl_backend.local.mapper.ImagesMapper;
import com.autodl_backend.local.pojo.entity.Images;
import com.autodl_backend.local.service.ImagesService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

}
