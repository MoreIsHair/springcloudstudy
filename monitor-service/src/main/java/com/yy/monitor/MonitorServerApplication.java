package com.yy.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author YY
 * @date 2020/1/2
 * @description
 */
@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.yy"})
public class MonitorServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorServerApplication.class,args);
    }
}
