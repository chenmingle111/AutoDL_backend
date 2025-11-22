package com.autodl_backend.local.pojo.page;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页响应
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> list;
    private Integer pageIndex;
    private Integer pageSize;
    private Integer maxPage;
    private Integer offset;
    private Long resultTotal;

    /**
     * 创建分页响应
     */
    public static <T> PageResponse<T> of(List<T> items, Long totalCount, PageRequest pageRequest) {
        pageRequest.validate();

        int maxPage = (int) Math.ceil((double) totalCount / pageRequest.getPageSize());
        if (maxPage < 1) {
            maxPage = 1;
        }

        PageResponse<T> response = new PageResponse<>();
        response.setList(items);
        response.setPageIndex(pageRequest.getPageIndex());
        response.setPageSize(pageRequest.getPageSize());
        response.setMaxPage(maxPage);
        response.setOffset(pageRequest.getOffset());
        response.setResultTotal(totalCount);

        return response;
    }
}
