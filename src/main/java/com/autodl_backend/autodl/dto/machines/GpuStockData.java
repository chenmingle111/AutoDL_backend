package com.autodl_backend.autodl.dto.machines;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Map;

/**
 * GPU库存数据响应DTO
 * 用于处理API返回的GPU库存信息，格式为：
 * [
 *   {
 *     "RTX 4090": {
 *       "idle_gpu_num": 215,
 *       "total_gpu_num": 2285
 *     }
 *   },
 *   ...
 * ]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GpuStockData {
    /**
     * GPU库存信息列表
     * 列表中每个元素是一个Map，键是GPU型号，值是库存信息
     */
    private List<Map<String, GpuStockInfo>> list;
}
