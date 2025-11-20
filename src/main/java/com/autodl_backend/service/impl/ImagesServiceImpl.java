package com.autodl_backend.service.impl;

import com.autodl_backend.mapper.ImagesMapper;
import com.autodl_backend.pojo.Images;
import com.autodl_backend.service.ImagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Images表的服务层实现类
 */
@Service
public class ImagesServiceImpl extends ServiceImpl<ImagesMapper, Images> implements ImagesService {
}
