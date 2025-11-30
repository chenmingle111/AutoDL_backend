package com.autodl_backend.local.service.impl;

import com.autodl_backend.autodl.client.AutoDLClient;
import com.autodl_backend.autodl.dto.machines.*;
import com.autodl_backend.local.mapper.BlacklistMapper;
import com.autodl_backend.local.mapper.ContainersMapper;
import com.autodl_backend.local.pojo.entity.Blacklist;
import com.autodl_backend.local.pojo.entity.Containers;
import com.autodl_backend.local.pojo.entity.GpuStock;
import com.autodl_backend.local.service.ManagementService;
import com.autodl_backend.util.AutodlFataConverter.MachinesConverter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Implementation of ManagementService.
 */
@Slf4j
@Service
public class ManagementServiceImpl extends ServiceImpl<BlacklistMapper, Blacklist> implements ManagementService {

    @Autowired
    private AutoDLClient autoDLClient;

    @Autowired
    private com.autodl_backend.local.mapper.ContainersMapper containersMapper;

    @Autowired
    private com.autodl_backend.local.mapper.GpuStockMapper gpuStockMapper;


    @Override
    public Object setBlacklist(SetBlacklistReq req) {
        // 从请求中获取uid
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        String uid = (String) request.getAttribute("uid");

        // 验证容器uuuid为空
        if (req.getDeploymentContainerUuid() == null || req.getDeploymentContainerUuid().isEmpty()) {
            throw new RuntimeException("deploymentContainerUuid为空");
        }

        // 记录用户操作日志
        log.info("用户[{}]正在设置容器[{}]的调度黑名单", uid, req.getDeploymentContainerUuid());

        // 验证容器uuid不存在
        Containers container = containersMapper.selectOne(
            new QueryWrapper<Containers>()
                .eq("container_uuid", req.getDeploymentContainerUuid())
        );
        if (container == null) {
            throw new RuntimeException("容器不存在");
        }

        // 使用转换器将黑名单请求转换为实体
        Blacklist blacklist = MachinesConverter.convertToBlacklist(req, container.getMachineUuid());

        // 调用AutoDL客户端设置黑名单
        Object response = autoDLClient.setBlacklist(req);

        // 保存黑名单到数据库
        save(blacklist);

        // 记录日志
        log.info("用户[{}]成功设置容器[{}]的调度黑名单", uid, req.getDeploymentContainerUuid());

        return response;
    }


    @Override
    public GpuStockData getGpuStock(GpuStockReq req) {
        // 从请求中获取uid
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        String uid = (String) request.getAttribute("uid");

        // 验证请求参数
        if (req.getRegionSign() == null || req.getRegionSign().isEmpty()) {
            throw new RuntimeException("regionSign为空");
        }

        // 记录用户操作日志
        log.info("用户[{}]正在获取地区[{}]的GPU库存", uid, req.getRegionSign());

        // 使用转换器将GPU库存请求转换为实体
        GpuStock gpuStockEntity = MachinesConverter.convertToGpuStock(req);

        // 调用AutoDL客户端获取GPU库存
        GpuStockData gpuStock = autoDLClient.getGpuStock(req);

        // 如果获取到数据，将其转换为本地实体并保存到数据库
        if (gpuStock != null && gpuStock.getList() != null) {
            // 使用转换器将GPU库存列表转换为实体列表
            GpuStock gpuStockList =
                MachinesConverter.convertToGpuStock(gpuStock,gpuStockEntity);

            // 保存到数据库
            for (Map<GpuInfo, GpuStockInfo> map: gpuStock.getList()) {
                for (Map.Entry<GpuInfo, GpuStockInfo> entry : map.entrySet()){
                    gpuStockEntity.setGpuName(entry.getKey().getGpuName());
                    gpuStockEntity.setIdleGpuNum(entry.getValue().getIdleGpuNum());
                    gpuStockEntity.setTotalGpuNum(entry.getValue().getTotalGpuNum());
                    gpuStockEntity.setQueryTime(java.time.LocalDateTime.now());
                    gpuStockEntity.setUid(uid);

                    // 保存到数据库
                    gpuStockMapper.insert(gpuStockEntity);
                }
            }

            // 记录日志
            log.info("用户[{}]成功获取地区[{}]的GPU库存，共[{}]种型号", 
                uid, req.getRegionSign(), gpuStockEntity.getTotalGpuNum());
        }

        // 记录日志
        log.info("用户[{}]成功获取地区[{}]的GPU库存", uid, req.getRegionSign());

        return gpuStock;
    }
}
