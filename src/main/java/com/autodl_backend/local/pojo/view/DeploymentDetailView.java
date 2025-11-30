package com.autodl_backend.local.pojo.view;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DeploymentDetailView {
    private Integer id;
    private String uid;
    private String deploymentUuid;
    private String deploymentName;
    private String deploymentType;
    private String deploymentStatus;
    private Integer replicaNum;
    private Integer parallelismNum;
    private Boolean reuseContainer;
    private Integer startingNum;
    private Integer runningNum;
    private Integer finishedNum;
    private String imageUuid;
    private String imageName;
    private BigDecimal priceEstimates;
    private LocalDateTime deploymentCreatedAt;
    private LocalDateTime deploymentUpdatedAt;
    private LocalDateTime stoppedAt;
    private Integer cudaV;
    private Integer gpuNum;
    private Integer cpuNumFrom;
    private Integer cpuNumTo;
    private Long memorySizeFrom;
    private Long memorySizeTo;
    private Integer priceFrom;
    private Integer priceTo;
    private String cmd;
    private String supportGpuNames; // 拼接的GPU型号字符串
}
