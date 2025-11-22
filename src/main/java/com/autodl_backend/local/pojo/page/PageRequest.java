package com.autodl_backend.local.pojo.page;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 分页请求参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {
    private Integer pageIndex = 1;
    private Integer pageSize = 10;
    private Integer offset = 0;

    /**
     * 参数校验和标准化
     */
    public void validate() {
        if (pageIndex == null || pageIndex < 1) {
            pageIndex = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        if (pageSize > 100) {  // 限制最大页面大小
            pageSize = 100;
        }
        if (offset == null || offset < 0) {
            offset = 0;
        }
    }

    /**
     * 计算SQL LIMIT值
     */
    public Integer getLimit() {
        validate();
        return pageSize;
    }

    /**
     * 计算SQL OFFSET值
     */
    public Integer getSqlOffset() {
        validate();
        if (offset > 0) {
            return offset;
        }
        return (pageIndex - 1) * pageSize;
    }
}