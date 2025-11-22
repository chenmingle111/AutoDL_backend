package com.autodl_backend.autodl.dto.container;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Info DTO for container information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContainerInfo {
    @JsonProperty("ssh_command")
    private String sshCommand;

    @JsonProperty("root_password")
    private String rootPassword;

    @JsonProperty("service_url")
    private String serviceUrl;

    @JsonProperty("proxy_host")
    private String proxyHost;

    @JsonProperty("custom_port")
    private Integer customPort;
}
