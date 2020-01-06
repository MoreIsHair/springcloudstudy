package com.yy.common.log;

import com.yy.common.log.aspect.LogAspect;
import com.yy.common.log.event.LogListener;
import com.yy.user.api.feign.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 */
@EnableAsync
@Configuration
@ConditionalOnWebApplication
public class LogAutoConfiguration {

    @Autowired
    private UserServiceClient userServiceClient;

    @Bean
    public LogListener sysLogListener() {
        return new LogListener(userServiceClient);
    }

    @Bean
    public LogAspect sysLogAspect() {
        return new LogAspect();
    }
}
