package com.yy.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author YY
 * @date 2020/1/2
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
@ComponentScan(basePackages = {"com.yy"})
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class,args);
    }
}
