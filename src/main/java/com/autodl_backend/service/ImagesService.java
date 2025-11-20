package com.autodl_backend.service;

import com.autodl_backend.pojo.Images;
import com.autodl_backend.pojo.page.PageRequest;
import com.autodl_backend.pojo.page.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * Images表的服务层接口
 */
public interface ImagesService extends IService<Images> {

    PageResponse<Images> listPage(PageRequest pageRequest);
}
