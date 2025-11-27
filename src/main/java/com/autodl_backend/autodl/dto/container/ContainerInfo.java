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

    @JsonProperty("service_6006_port_url")
    private String service6006PortUrl;

    @JsonProperty("service_6008_port_url")
    private String service6008PortUrl;
}
