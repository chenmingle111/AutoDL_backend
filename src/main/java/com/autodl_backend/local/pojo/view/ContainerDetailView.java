package com.autodl_backend.local.pojo.view;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 容器详情视图VO类（对应数据库视图container_detail_view）
 */
@Data
public class ContainerDetailView {

    /**
     * 容器ID
     */
    private Integer id;

    /**
     * 容器唯一标识
     */
    private String containerUuid;

    /**
     * 关联部署UUID
     */
    private String deploymentUuid;

    /**
     * 部署名称
     */
    private String deploymentName;

    /**
     * 部署类型（ReplicaSet/Job/Container）
     */
    private String deploymentType;

    /**
     * 关联主机UUID
     */
    private String machineUuid;

    /**
     * 主机区域
     */
    private String region;

    /**
     * 主机地址
     */
    private String hostAddress;

    /**
     * 容器状态（creating/running/shutdown/error）
     */
    private String containerStatus;

    /**
     * 分配的GPU型号
     */
    private String gpuName;

    /**
     * 分配的GPU数量
     */
    private Integer gpuNum;

    /**
     * 分配的CPU核心数
     */
    private Integer cpuNum;

    /**
     * 分配的内存（字节）
     */
    private Long memorySize;

    /**
     * 关联镜像UUID
     */
    private String imageUuid;

    /**
     * 镜像名称
     */
    private String imageName;

    /**
     * 基准价格（元*1000）
     */
    private Integer price;

    /**
     * SSH端口
     */
    private Integer sshPort;

    /**
     * SSH密码（加密存储）
     */
    private String sshPassword;

    /**
     * 自定义服务地址
     */
    private String serviceUrl;

    /**
     * 容器启动时间
     */
    private LocalDateTime startedAt;

    /**
     * 容器停止时间
     */
    private LocalDateTime stoppedAt;

    /**
     * 容器创建时间
     */
    private LocalDateTime containerCreatedAt;

    /**
     * 容器更新时间
     */
    private LocalDateTime containerUpdatedAt;

    /**
     * 是否已释放
     */
    private Boolean released;
}
