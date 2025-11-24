package com.autodl_backend.local.service.validation;

import com.autodl_backend.local.exception.ContainerAlreadyExistsException;
import com.autodl_backend.local.exception.ContainerNotFoundException;
import com.autodl_backend.local.exception.ContainerStartFailedException;
import com.autodl_backend.local.exception.ContainerStatusException;
import com.autodl_backend.local.exception.ContainerStopFailedException;
import com.autodl_backend.local.mapper.ContainersMapper;
import com.autodl_backend.local.pojo.entity.Containers;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 容器验证服务
 * 集中处理所有容器相关的验证逻辑
 */
@Service
public class ContainerValidationService {

    @Autowired
    private ContainersMapper containersMapper;

    /**
     * 验证容器是否存在
     *
     * @param containerUuid 容器UUID
     * @return 容器对象
     * @throws ContainerNotFoundException 如果容器不存在
     */
    public Containers validateContainerExists(String containerUuid) throws ContainerNotFoundException {
        if (!StringUtils.hasText(containerUuid)) {
            throw new ContainerNotFoundException("容器UUID不能为空");
        }

        QueryWrapper<Containers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("container_uuid", containerUuid)
                   .eq("is_deleted", 0);

        Containers container = containersMapper.selectOne(queryWrapper);
        if (container == null) {
            throw new ContainerNotFoundException("容器不存在: UUID " + containerUuid);
        }

        return container;
    }

    /**
     * 验证容器是否可以启动
     *
     * @param containerUuid 容器UUID
     * @return 容器对象
     * @throws ContainerNotFoundException 如果容器不存在
     * @throws ContainerStartFailedException 如果容器无法启动
     * @throws ContainerStatusException 如果容器状态异常
     */
    public Containers validateContainerCanStart(String containerUuid)
            throws ContainerNotFoundException, ContainerStartFailedException, ContainerStatusException {
        Containers container = validateContainerExists(containerUuid);

        // 检查容器状态是否允许启动
        if ("running".equals(container.getStatus())) {
            throw new ContainerStatusException("容器已在运行中，无法重复启动");
        }

        if ("error".equals(container.getStatus())) {
            throw new ContainerStartFailedException("容器处于错误状态，无法启动");
        }

        // 其他状态检查...

        return container;
    }

    /**
     * 验证容器是否可以停止
     *
     * @param containerUuid 容器UUID
     * @return 容器对象
     * @throws ContainerNotFoundException 如果容器不存在
     * @throws ContainerStopFailedException 如果容器无法停止
     * @throws ContainerStatusException 如果容器状态异常
     */
    public Containers validateContainerCanStop(String containerUuid)
            throws ContainerNotFoundException, ContainerStopFailedException, ContainerStatusException {
        Containers container = validateContainerExists(containerUuid);

        // 检查容器状态是否允许停止
        if ("stopped".equals(container.getStatus())) {
            throw new ContainerStatusException("容器已停止，无需重复停止");
        }

        if ("error".equals(container.getStatus())) {
            throw new ContainerStopFailedException("容器处于错误状态，无法正常停止");
        }

        // 其他状态检查...

        return container;
    }

    /**
     * 验证容器是否已存在（用于创建前检查）
     *
     * @param containerUuid 容器UUID
     * @throws ContainerAlreadyExistsException 如果容器已存在
     */
    public void validateContainerNotExists(String containerUuid) throws ContainerAlreadyExistsException {
        if (!StringUtils.hasText(containerUuid)) {
            return; // 如果UUID为空，则不存在，无需检查
        }

        QueryWrapper<Containers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("container_uuid", containerUuid)
                   .eq("is_deleted", 0);

        Containers container = containersMapper.selectOne(queryWrapper);
        if (container != null) {
            throw new ContainerAlreadyExistsException("容器已存在: UUID " + containerUuid);
        }
    }
}
