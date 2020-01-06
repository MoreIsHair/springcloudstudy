package com.yy.getaway.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Swagger聚合文档
 * 目前问题：结合动态更新路由之后，GatewayProperties获取不到新的路由列表，导致swagger-ui显示不了
 * 解决办法：从Redis里读取路由数据
 */
@Slf4j
@Component
@Primary
@AllArgsConstructor
public class RegistrySwaggerResourcesProvider implements SwaggerResourcesProvider {

    private static final String API_URI = "/v2/api-docs";

    private final RouteLocator routeLocator;

    private final SwaggerProviderConfig swaggerProviderConfig;

    private final RedisTemplate redisTemplate;


    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        // 取出gateway的route
        routeLocator.getRoutes().subscribe(route -> {
            // 根据文档提供者过滤
            if (swaggerProviderConfig.getProviders().contains(route.getId())){
                routes.add(route.getId());
            }
        });
        // 结合配置的route-路径(Path)，和route过滤，只获取有效的route节点
        resources.addAll(swaggerResource(new HashMap<>(16)));
        return resources;
    }

    /**
     * 服务信息
     * @return SwaggerResource
     */
    private List<SwaggerResource> swaggerResource(Map<String,String> nameAndLocation) {
        ArrayList<SwaggerResource> swaggerResources = new ArrayList<>(nameAndLocation.size());
        nameAndLocation.forEach((key, value)->{
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(key);
            swaggerResource.setLocation(value);
            swaggerResource.setSwaggerVersion("0.0.1");
            swaggerResources.add(swaggerResource);
        });
        return swaggerResources;
    }
}
