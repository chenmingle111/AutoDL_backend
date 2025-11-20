package com.autodl_backend.DTO;

import com.autodl_backend.pojo.enums.DeploymentStatus;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeploymentDTO {

    private Integer id; //部署id
    private Integer uid; //用户唯一标识
    @NotNull
    private String uuid; //部署唯一标识

    @NotNull
    private String name; // 部署名称

    @NotNull
    private String deployment_type; // 部署类型（ReplicaSet/Job/Container）

    private DeploymentStatus status; //部署当前状态
    private Integer replica_num; // 副本数量
    private Integer parallelism_num; // Job 并行容器数（仅 Job 类型必填）
    private Boolean reuse_container = false; // 是否复用容器（默认 false）
    private Integer starting_num;  //开始的部署数量
    private Integer running_num;  //运行中的部署数量
    private Integer finished_num; //完成的部署数量

    @NotNull
    private String imageUuid;  //镜像的唯一标识

    private Integer priceEstimates;  //价格估算
    private LocalDateTime createdAt;  //部署创建时间
    private LocalDateTime updatedAt;  //部署更新时间
    private LocalDateTime stoppedAt;  //部署停止时间

    // 嵌套的容器模板信息（对应前端的 container_template 对象）
    @NotNull
    private ContainerTemplateDTO container_template;

    /**
     * 容器模板子 DTO（对应前端 container_template 嵌套对象）
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContainerTemplateDTO {
        private List<String> gpu_name_set; // GPU 型号列表（前端有该字段，实体类需补充）

        @NotNull
        private Integer cuda_v; // CUDA 版本

        @NotNull
        private Integer gpu_num; // GPU 数量

        @NotNull
        private Integer cpu_num_from; // CPU 核心下限

        @NotNull
        private Integer cpu_num_to; // CPU 核心上限

        @NotNull
        private Long memory_size_from; // 内存下限（字节）

        @NotNull
        private Long memory_size_to; // 内存上限（字节）

        @NotNull
        private Integer price_from; // 价格下限

        @NotNull
        private Integer price_to; // 价格上限

        @NotNull
        private String cmd; // 容器启动命令

        @NotNull
        private String image_uuid; // 关联镜像 UUID
    }
}
