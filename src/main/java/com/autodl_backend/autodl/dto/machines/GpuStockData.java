package com.autodl_backend.autodl.dto.machines;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Map;

/**
 * DTO for GPU stock data response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GpuStockData {
    private List<Map<String, GpuStockInfo>> list;
}
