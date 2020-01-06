package com.yy.msc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author YY
 * @date 2020/1/2
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableFeignClients
@EnableCircuitBreaker
@ComponentScan(basePackages = {"com.yy"})
public class MscServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MscServerApplication.class,args);
    }
}
