package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LR
 * @version 1.0
 * @date 2020/11/23 19:42
 * @Description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class, args);
    }
}
