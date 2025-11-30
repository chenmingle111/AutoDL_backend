package com.autodl_backend.util.AutodlFataConverter;

import com.autodl_backend.autodl.dto.machines.GpuStockData;
import com.autodl_backend.autodl.dto.machines.GpuStockInfo;
import com.autodl_backend.autodl.dto.machines.GpuStockReq;
import com.autodl_backend.autodl.dto.machines.SetBlacklistReq;
import com.autodl_backend.local.pojo.entity.Blacklist;
import com.autodl_backend.local.pojo.entity.Containers;
import com.autodl_backend.local.pojo.entity.GpuStock;
import com.autodl_backend.local.pojo.enums.ContainerStatus;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * AutoDL机器数据转换工具类
 * 用于将AutoDL API返回的DTO对象转换为本地实体对象
 */
@Slf4j
public class MachinesConverter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //设置黑名单的请求
    /**
     * 将黑名单请求转换为本地实体
     * @param req 黑名单请求
     * @param machineUuid 机器UUID
     * @return 本地实体
     */
    public static Blacklist convertToBlacklist(SetBlacklistReq req, String machineUuid) {
        Blacklist blacklist = new Blacklist();

        // 设置基本信息
        blacklist.setMachineUuid(machineUuid);

        // 设置过期时间（分钟转换为秒）
        if (req.getExpireInMinutes() != null) {
            blacklist.setExpireInMinutes(req.getExpireInMinutes() * 60);
        }

        // 设置备注信息
        if (req.getComment() != null) {
            blacklist.setComment(req.getComment());
        }

        return blacklist;
    }

    //GPU库存信息

    /**
     * 将GPU库存请求转换为本地实体
     * @param req GPU库存请求
     * @return 本地实体
     */
    public static GpuStock convertToGpuStock(GpuStockReq req) {
        GpuStock gpuStock = new com.autodl_backend.local.pojo.entity.GpuStock();

        // 设置地区标识
        if (req.getRegionSign() != null) {
            gpuStock.setRegionSign(req.getRegionSign());
        }

        // 设置CUDA版本范围
        if (req.getCudaVFrom() != null) {
            gpuStock.setCudaVFrom(req.getCudaVFrom());
        }
        if (req.getCudaVTo() != null) {
            gpuStock.setCudaVTo(req.getCudaVTo());
        }

        // 设置GPU型号列表
        if (req.getGpuNameSet() != null && !req.getGpuNameSet().isEmpty()) {
            gpuStock.setGpuNameSet(String.join(",", req.getGpuNameSet()));
        }

        // 设置内存范围
        if (req.getMemorySizeFrom() != null) {
            gpuStock.setMemorySizeFrom(req.getMemorySizeFrom());
        }
        if (req.getMemorySizeTo() != null) {
            gpuStock.setMemorySizeTo(req.getMemorySizeTo());
        }

        // 设置CPU核心数范围
        if (req.getCpuNumFrom() != null) {
            gpuStock.setCpuNumFrom(req.getCpuNumFrom());
        }
        if (req.getCpuNumTo() != null) {
            gpuStock.setCpuNumTo(req.getCpuNumTo());
        }

        // 设置价格范围
        if (req.getPriceFrom() != null) {
            gpuStock.setPriceFrom(req.getPriceFrom());
        }
        if (req.getPriceTo() != null) {
            gpuStock.setPriceTo(req.getPriceTo());
        }

        // 设置查询时间
        gpuStock.setQueryTime(LocalDateTime.now());

        log.info("GPU库存查询请求 - 地区: {}, CUDA版本范围: {}-{}, GPU型号: {}, 内存范围: {}-{}GB, CPU核心数范围: {}-{}, 价格范围: {}-{}", 
            req.getRegionSign(), 
            req.getCudaVFrom(), req.getCudaVTo(),
            req.getGpuNameSet(),
            req.getMemorySizeFrom(), req.getMemorySizeTo(),
            req.getCpuNumFrom(), req.getCpuNumTo(),
            req.getPriceFrom(), req.getPriceTo());

        return gpuStock;
    }

    /**
     * 将GPU库存响应转换为本地实体
     */
    public static GpuStock convertToGpuStock(GpuStockData data, GpuStock gpuStock) {

        return gpuStock;
    }

}
