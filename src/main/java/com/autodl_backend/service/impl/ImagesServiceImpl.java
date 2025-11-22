package com.autodl_backend.service.impl;

import com.autodl_backend.integration.AutoDLClient;
import com.autodl_backend.integration.DTO.AutoDLImageListData;
import com.autodl_backend.integration.DTO.AutoDLImageRequest;
import com.autodl_backend.integration.DTO.AutoDLResp;
import com.autodl_backend.mapper.ImagesMapper;
import com.autodl_backend.pojo.Images;
import com.autodl_backend.pojo.enums.ImageStatus;
import com.autodl_backend.pojo.page.PageRequest;
import com.autodl_backend.pojo.page.PageResponse;
import com.autodl_backend.service.ImagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Images表的服务层实现类
 */
@Slf4j
@Service
public class ImagesServiceImpl extends ServiceImpl<ImagesMapper, Images> implements ImagesService {

    @Autowired
    private AutoDLClient autoDLClient;

    @Override
    public PageResponse<Images> listPage(PageRequest pageRequest) {
        // 验证并标准化分页参数
        pageRequest.validate();

        // 构建AutoDL API请求参数
        AutoDLImageRequest apiRequest = new AutoDLImageRequest(
                pageRequest.getPageIndex(),
                pageRequest.getPageSize(),
                pageRequest.getOffset());

        // 调用AutoDL API
        // 使用 postForData 获取 data 部分 (AutoDLImageListData)
        AutoDLImageListData data = autoDLClient.postForData(
                "https://private.autodl.com/api/v1/dev/image/private/list",
                apiRequest,
                new ParameterizedTypeReference<AutoDLResp<AutoDLImageListData>>() {
                });

        // 此时 data 已经是 AutoDLImageListData 对象，且 list 中的元素已经是 Images 对象 (由 Jackson 自动映射)
        // 但可能需要进一步处理（如状态枚举转换，如果 JSON 中的值与枚举不直接匹配）
        // 假设 Images 类中的 @JsonProperty 和类型转换能正确处理大部分字段
        // 如果状态是字符串，Images 中是枚举，可能需要自定义反序列化或后处理
        // 这里假设 Images 能够直接接收或我们需要手动处理一些字段

        List<Images> imagesList = data.getList();
        if (imagesList == null) {
            imagesList = new ArrayList<>();
        }

        // 使用PageResponse构建响应
        PageResponse<Images> pageResponse = new PageResponse<>();
        pageResponse.setList(imagesList);
        pageResponse.setPageIndex(data.getPageIndex());
        pageResponse.setPageSize(data.getPageSize());
        pageResponse.setMaxPage(data.getMaxPage());
        pageResponse.setOffset(data.getOffset());
        // 如果 API 返回 result_total 则使用，否则用 list size (通常 API 会返回 total)
        pageResponse.setResultTotal(data.getResultTotal() != null ? data.getResultTotal() : (long) imagesList.size());

        return pageResponse;
    }

}
