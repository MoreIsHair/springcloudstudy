package com.yy.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author YY
 * @date 2019/12/31
 * @description
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.yy"})
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
@EnableAuthorizationServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class,args);
    }
}
