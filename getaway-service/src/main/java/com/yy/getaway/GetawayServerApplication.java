package com.yy.getaway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author YY
 * @date 2020/1/2
 * @description
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.yy"})
@EnableDiscoveryClient
@EnableCircuitBreaker
public class GetawayServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GetawayServerApplication.class,args);
    }
}
