package com.yy.common.core.config;

import com.yy.common.core.properties.SnowflakeProperties;
import com.yy.common.core.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ID生成配置
 * @author YY
 * @date 2020/1/3
 */
@Configuration
@EnableConfigurationProperties(SnowflakeProperties.class)
public class SnowFlake {

    @Autowired
    private SnowflakeProperties snowflakeProperties;

    @Bean
    public SnowflakeIdWorker initTokenWorker() {
        return new SnowflakeIdWorker(Integer.parseInt(snowflakeProperties.getWorkId()), Integer.parseInt(snowflakeProperties.getDataCenterId()));
    }
}
