package com.autodl_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.autodl_backend.local.mapper")
public class AutoDlBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoDlBackendApplication.class, args);
    }

}
