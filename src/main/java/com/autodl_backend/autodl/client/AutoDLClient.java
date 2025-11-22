package com.autodl_backend.autodl.client;

import com.autodl_backend.autodl.config.AutoDLProperties;
import com.autodl_backend.autodl.dto.AutoDLCreateReq;
import com.autodl_backend.autodl.dto.AutoDLResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Client for interacting with AutoDL API.
 * Encapsulates HTTP requests.
 */
@Component
public class AutoDLClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AutoDLProperties autoDLProperties;

    public AutoDLResp createInstance(AutoDLCreateReq req) {
        String url = autoDLProperties.getUrl() + "/instance/create";
        // Add headers with token if necessary
        // HttpHeaders headers = new HttpHeaders();
        // headers.set("Authorization", autoDLProperties.getToken());
        // HttpEntity<AutoDLCreateReq> entity = new HttpEntity<>(req, headers);

        // For now, just a simple post
        return restTemplate.postForObject(url, req, AutoDLResp.class);
    }

    public AutoDLResp getInstanceStatus(String instanceId) {
        String url = autoDLProperties.getUrl() + "/instance/" + instanceId;
        return restTemplate.getForObject(url, AutoDLResp.class);
    }
}
