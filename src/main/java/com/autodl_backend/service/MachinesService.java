package com.autodl_backend.service;

import com.autodl_backend.pojo.Machines;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
 * Machines表的服务层接口
 */
public interface MachinesService extends IService<Machines> {
    /**
     * 获取弹性部署GPU库存
     */
    HashMap<String, Machines> getGpuStock();
}
