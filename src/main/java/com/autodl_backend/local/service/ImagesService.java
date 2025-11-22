package com.autodl_backend.local.service;

import com.autodl_backend.local.pojo.entity.Images;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * Images表的服务层接口
 */
public interface ImagesService extends IService<Images> {

    /**
     * 获取私有镜像列表
     * 
     * @param pageIndex 页码
     * @param pageSize  每页条目数
     * @return 镜像列表数据
     */
    com.autodl_backend.autodl.dto.image.PrivateImageListData getPrivateImages(int pageIndex, int pageSize);
}
