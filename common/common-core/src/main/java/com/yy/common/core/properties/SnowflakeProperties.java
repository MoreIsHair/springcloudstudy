package com.yy.common.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @author YY
 * @date 2020/1/2
 * @description
 */
@Data
@ConfigurationProperties(prefix = "cluster")
@Configuration
public class SnowflakeProperties {
    /**
     * 工作节点ID
     */
    private String workId;

    /**
     * 数据中心ID
     */
    private String dataCenterId;
}

