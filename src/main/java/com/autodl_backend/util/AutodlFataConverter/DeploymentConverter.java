package com.autodl_backend.util.AutodlFataConverter;

import com.autodl_backend.autodl.dto.deployment.CreateDeploymentData;
import com.autodl_backend.autodl.dto.deployment.CreateDeploymentReq;
import com.autodl_backend.autodl.dto.deployment.DeploymentItem;
import com.autodl_backend.local.pojo.entity.Deployments;
import com.autodl_backend.local.pojo.enums.DeploymentStatus;
import com.autodl_backend.local.pojo.enums.DeploymentType;
import com.autodl_backend.util.DateTimeHelper;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * AutoDL部署数据转换工具类
 * 用于将AutoDL API返回的DTO对象转换为本地实体对象
 */
@Slf4j
public class DeploymentConverter {


    /**
     * 将创建部署请求转换为Deployments实体
     * @param req 创建部署请求
     * @param uid 用户ID
     * @return Deployments实体
     */
    public static Deployments convertToDeployment(CreateDeploymentReq req, String uid) {
        Deployments deployment = new Deployments();

        // 设置基本信息
        deployment.setUid(uid);
        deployment.setName(req.getName());

        // 转换部署类型
        if (req.getDeploymentType() != null) {
            try {
                deployment.setDeploymentType(req.getDeploymentType());
            } catch (IllegalArgumentException e) {
                log.warn("Unknown deployment type: {}, using default", req.getDeploymentType());
            }
        }

        // 设置其他属性
        deployment.setReplicaNum(req.getReplicaNum());
        deployment.setParallelismNum(req.getParallelismNum());
        deployment.setReuseContainer(req.getReuseContainer());

        // 设置镜像UUID
        if (req.getContainerTemplate() != null) {
            deployment.setImageUuid(req.getContainerTemplate().getImageUuid());
        }

        // 设置初始状态
        deployment.setStatus(DeploymentStatus.CREATING);
        deployment.setStartingNum(0);
        deployment.setRunningNum(0);
        deployment.setFinishedNum(0);

        return deployment;
    }

    /**
     * 将创建部署响应转换为Deployments实体
     * @param data 创建部署响应
     * @param deployment 原始部署实体
     * @return 更新后的Deployments实体
     */
    public static Deployments convertToDeployment(CreateDeploymentData data, Deployments deployment) {
        if (data != null && data.getDeploymentUuid() != null) {
            deployment.setDeploymentUuid(data.getDeploymentUuid());
        }
        return deployment;
    }

    /**
     * 将部署项转换为Deployments实体
     * @param item AutoDL返回的部署项
     * @param uid 用户ID
     * @return Deployments实体
     */
    public static Deployments convertToDeployment(DeploymentItem item, String uid) {
        Deployments deployment = new Deployments();

        // 设置基本信息
        deployment.setUid(uid);
        deployment.setDeploymentUuid(item.getUuid());
        deployment.setName(item.getName());

        // 转换部署类型
        if (item.getDeploymentType() != null) {
            try {
                deployment.setDeploymentType(DeploymentType.valueOf(item.getDeploymentType().toUpperCase()));
            } catch (IllegalArgumentException e) {
                log.warn("Unknown deployment type: {}, using default", item.getDeploymentType());
            }
        }

        // 转换状态
        if (item.getStatus() != null) {
            try {
                deployment.setStatus(DeploymentStatus.valueOf(item.getStatus().toUpperCase()));
            } catch (IllegalArgumentException e) {
                log.warn("Unknown deployment status: {}, using default", item.getStatus());
                deployment.setStatus(DeploymentStatus.STOPPED);
            }
        }

        // 设置其他属性
        deployment.setReplicaNum(item.getReplicaNum());
        deployment.setParallelismNum(item.getParallelismNum());
        deployment.setReuseContainer(item.getReuseContainer());
        deployment.setStartingNum(item.getStartingNum());
        deployment.setRunningNum(item.getRunningNum());
        deployment.setFinishedNum(item.getFinishedNum());
        deployment.setImageUuid(item.getImageUuid());

        // 设置价格预估
        if (item.getPriceEstimates() != null) {
            try {
                deployment.setPriceEstimates(new BigDecimal(String.valueOf(item.getPriceEstimates())));
            } catch (NumberFormatException e) {
                log.warn("Invalid price estimates: {}", item.getPriceEstimates());
                deployment.setPriceEstimates(BigDecimal.ZERO);
            }
        }

        // 转换时间
        if (item.getCreatedAt() != null) {
            try {
                deployment.setCreatedAt(DateTimeHelper.parseDateTime(item.getCreatedAt()));
            } catch (Exception e) {
                log.warn("Failed to parse created_at: {}", item.getCreatedAt());
                deployment.setCreatedAt(LocalDateTime.now());
            }
        }

        if (item.getUpdatedAt() != null) {
            try {
                deployment.setUpdatedAt(DateTimeHelper.parseDateTime(item.getUpdatedAt()));
            } catch (Exception e) {
                log.warn("Failed to parse updated_at: {}", item.getUpdatedAt());
                deployment.setUpdatedAt(LocalDateTime.now());
            }
        }

        if (item.getStoppedAt() != null) {
            try {
                deployment.setStoppedAt(DateTimeHelper.parseDateTime(item.getStoppedAt()));
            } catch (Exception e) {
                log.warn("Failed to parse stopped_at: {}", item.getStoppedAt());
            }
        }

        return deployment;
    }

    /**
     * 更新现有部署对象
     * @param deployment 现有部署对象
     * @param item AutoDL返回的部署项
     * @param uid 用户ID
     * @return 更新后的Deployments实体
     */
    public static void updateDeployment(Deployments deployment, DeploymentItem item, String uid) {
        // 保留原有ID和其他不需要更新的字段

        // 更新基本信息
        deployment.setUid(uid);
        deployment.setName(item.getName());

        // 转换部署类型
        if (item.getDeploymentType() != null) {
            try {
                deployment.setDeploymentType(DeploymentType.valueOf(item.getDeploymentType().toUpperCase()));
            } catch (IllegalArgumentException e) {
                log.warn("Unknown deployment type: {}, using default", item.getDeploymentType());
            }
        }

        // 转换状态
        if (item.getStatus() != null) {
            try {
                deployment.setStatus(DeploymentStatus.valueOf(item.getStatus().toUpperCase()));
            } catch (IllegalArgumentException e) {
                log.warn("Unknown deployment status: {}, using default", item.getStatus());
                deployment.setStatus(DeploymentStatus.STOPPED);
            }
        }

        // 更新其他属性
        deployment.setReplicaNum(item.getReplicaNum());
        deployment.setParallelismNum(item.getParallelismNum());
        deployment.setReuseContainer(item.getReuseContainer());
        deployment.setStartingNum(item.getStartingNum());
        deployment.setRunningNum(item.getRunningNum());
        deployment.setFinishedNum(item.getFinishedNum());
        deployment.setImageUuid(item.getImageUuid());

        // 更新价格预估
        if (item.getPriceEstimates() != null) {
            try {
                deployment.setPriceEstimates(new BigDecimal(String.valueOf(item.getPriceEstimates())));
            } catch (NumberFormatException e) {
                log.warn("Invalid price estimates: {}", item.getPriceEstimates());
                deployment.setPriceEstimates(BigDecimal.ZERO);
            }
        }

        // 转换时间
        if (item.getCreatedAt() != null) {
            try {
                deployment.setCreatedAt(DateTimeHelper.parseDateTime(item.getCreatedAt()));
            } catch (Exception e) {
                log.warn("Failed to parse created_at: {}", item.getCreatedAt());
            }
        }

        if (item.getUpdatedAt() != null) {
            try {
                deployment.setUpdatedAt(DateTimeHelper.parseDateTime(item.getUpdatedAt()));
            } catch (Exception e) {
                log.warn("Failed to parse updated_at: {}", item.getUpdatedAt());
                deployment.setUpdatedAt(LocalDateTime.now());
            }
        }

        if (item.getStoppedAt() != null) {
            try {
                deployment.setStoppedAt(DateTimeHelper.parseDateTime(item.getStoppedAt()));
            } catch (Exception e) {
                log.warn("Failed to parse stopped_at: {}", item.getStoppedAt());
            }
        }
    }
}
