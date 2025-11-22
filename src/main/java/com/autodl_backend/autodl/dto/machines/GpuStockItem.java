package com.autodl_backend.autodl.dto.machines;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Map;

/**
 * DTO for GPU stock item.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GpuStockItem {
    private Map<String, GpuStockInfo> gpuStocks;

    /**
     * Create a GPU stock item with a single GPU type
     */
    public static GpuStockItem createSingle(String gpuType, GpuStockInfo stockInfo) {
        GpuStockItem item = new GpuStockItem();
        item.gpuStocks = Map.of(gpuType, stockInfo);
        return item;
    }
}
