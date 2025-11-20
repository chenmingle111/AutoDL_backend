package com.autodl_backend.controller;

import com.autodl_backend.DTO.DeploymentDTO;
import com.autodl_backend.DTO.StopDeploymentDTO;
import com.autodl_backend.DTO.UpdateReplicaNumDTO;
import com.autodl_backend.pojo.ContainerTemplates;
import com.autodl_backend.pojo.Deployments;
import com.autodl_backend.pojo.SchedulingBlacklist;
import com.autodl_backend.pojo.enums.DeploymentStatus;
import com.autodl_backend.pojo.enums.DeploymentType;
import com.autodl_backend.pojo.page.PageRequest;
import com.autodl_backend.pojo.page.PageResponse;
import com.autodl_backend.pojo.response.ApiResponse;
import com.autodl_backend.service.ContainerTemplatesService;
import com.autodl_backend.service.DeploymentsService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/dev/deployment")
public class DeploymentController {


    @Autowired
    private DeploymentsService deploymentsService;

    @Autowired
    private ContainerTemplatesService containerTemplatesService;

    /**
     * 接收前端创建部署的请求
     */
    @PostMapping
    public ApiResponse<String> createDeployment(@Validated @RequestBody DeploymentDTO dto) {
        // 1. 封装 Deployments 实体（部署表），设置前端传递的参数 + 系统默认值
        Deployments deployment = new Deployments();
        deployment.setName(dto.getName());
        // 部署类型：将前端字符串转换为枚举（DeploymentType 需有 valueOf 方法，或自定义转换）
        deployment.setDeploymentType(DeploymentType.valueOf(dto.getDeployment_type()));
        deployment.setReplicaNum(dto.getReplica_num());
        deployment.setParallelismNum(dto.getParallelism_num());
        deployment.setReuseContainer(dto.getReuse_container());
        // 系统默认值（前端无需传递）
        deployment.setUid(1); // 归属用户ID（实际应从登录态获取，如 Token 解析）
        deployment.setDeploymentUuid(UUID.randomUUID().toString().replace("-", "")); // 生成唯一标识
        deployment.setStatus(DeploymentStatus.CREATING); // 部署状态：待启动（根据你的枚举定义调整）
        deployment.setStartingNum(0); // 启动中容器数：初始 0
        deployment.setRunningNum(0); // 运行中容器数：初始 0
        deployment.setFinishedNum(0); // 已完成容器数：初始 0
        deployment.setImageUuid(dto.getContainer_template().getImage_uuid()); // 从容器模板中获取镜像 UUID
        deployment.setPriceEstimates(new BigDecimal("0.00")); // 预估费用：初始 0
        deployment.setIsDeleted(0); // 逻辑删除：未删除

        // 2. 保存部署信息到 deployments 表，获取自动生成的 deployment_id（主键）
        deploymentsService.save(deployment);
        Integer deploymentId = deployment.getId(); // 保存后，MyBatis-Plus 会自动回填主键 id

        // 3. 封装 ContainerTemplates 实体（容器模板表），绑定 deployment_id
        DeploymentDTO.ContainerTemplateDTO templateDto = dto.getContainer_template();
        ContainerTemplates containerTemplate = new ContainerTemplates();
        containerTemplate.setDeploymentId(deploymentId); // 关键：绑定部署 ID，建立关联
        containerTemplate.setCudaV(templateDto.getCuda_v());
        containerTemplate.setGpuNum(templateDto.getGpu_num());
        containerTemplate.setCpuNumFrom(templateDto.getCpu_num_from());
        containerTemplate.setCpuNumTo(templateDto.getCpu_num_to());
        containerTemplate.setMemorySizeFrom(templateDto.getMemory_size_from());
        containerTemplate.setMemorySizeTo(templateDto.getMemory_size_to());
        containerTemplate.setPriceFrom(templateDto.getPrice_from());
        containerTemplate.setPriceTo(templateDto.getPrice_to());
        containerTemplate.setCmd(templateDto.getCmd());
        // GPU 型号列表：如果数据库是字符串类型，用逗号拼接 List；如果是 JSON 类型，直接存字符串
        containerTemplate.setGpuNameSet(templateDto.getGpu_name_set());

        // 4. 保存容器模板信息到 container_templates 表
        containerTemplatesService.save(containerTemplate);

        //5.调用service方法
        String deploymentUuid = deploymentsService.createDeployment(dto);
        return ApiResponse.success(deploymentUuid);
    }
    /**
     * 获取部署列表
     */
    @PostMapping("/list")
    public ApiResponse<PageResponse<DeploymentDTO>> listDeployments(@Validated @RequestBody PageRequest pageRequest) {
        PageResponse<DeploymentDTO> pageResponse = deploymentsService.listDeployments(pageRequest);
        return ApiResponse.success(pageResponse);
    }

    /**
     * 设置副本数量
     */
    @PutMapping("/replica_num")
    public ApiResponse UpdateReplicaNum(@Validated @RequestBody UpdateReplicaNumDTO  updateReplicaNumDTO) {
        deploymentsService.updateReplicaNum(updateReplicaNumDTO);
        return ApiResponse.success();
    }

    /**
     * 停止部署
     */
    @PutMapping("/operate")
    public ApiResponse stopDeployment(@RequestBody StopDeploymentDTO stopDeploymentDTO) {
        deploymentsService.stopDeployment(stopDeploymentDTO);
        return ApiResponse.success();
    }

    /**
     * 删除部署
     */
    @DeleteMapping
    public ApiResponse deleteDeployment(@RequestBody @JsonProperty("deployment_uuid") String deploymentUuid) {
        deploymentsService.deleteDeployment(deploymentUuid);
        return ApiResponse.success();
    }

    /**
     * 设置调度黑名单
     */
    @PostMapping("/blacklist")
    public ApiResponse UpdateBlackList(@RequestBody SchedulingBlacklist blacklist) {
        deploymentsService.updateBlackList(blacklist);
        return ApiResponse.success();
    }
}
