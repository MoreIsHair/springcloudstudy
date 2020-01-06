package com.yy.common.security.config;

import com.yy.common.security.properties.FilterIgnorePropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 * @author YY
 * @date 2020/1/3
 * @description 资源服务器配置（每个向外提供服务的都属于资源服务器）
 */
@Configuration
@EnableResourceServer
@EnableConfigurationProperties(FilterIgnorePropertiesConfig.class)
public class CustomResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource_id";

    /**
     * 开放权限的URL
     */
    private final FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;

    @Autowired
    public CustomResourceServerConfig(FilterIgnorePropertiesConfig filterIgnorePropertiesConfig) {
        this.filterIgnorePropertiesConfig = filterIgnorePropertiesConfig;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(filterIgnorePropertiesConfig.getUrls().toArray(new String[0])).permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
        super.configure(http);
    }
}
