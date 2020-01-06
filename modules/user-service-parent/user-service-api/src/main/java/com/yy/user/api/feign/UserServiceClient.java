package com.yy.user.api.feign;

import com.yy.common.core.constant.ServerConstant;
import com.yy.common.core.model.Log;
import com.yy.common.core.vo.UserVo;
import com.yy.common.fegin.config.CustomFeignConfig;
import com.yy.user.api.feign.factory.UserServiceClientFallbackFactory;
import com.yy.user.api.feign.module.Menu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author YY
 * @date 2020/1/6
 * @description
 */
@FeignClient(value = ServerConstant.USER_SERVICE, configuration = CustomFeignConfig.class, fallbackFactory = UserServiceClientFallbackFactory.class)
public interface UserServiceClient {

    /**
     * 根据用户名获取用户详细信息
     * @param identifier identifier
     * @return UserVo
     */
    @GetMapping("/v1/user/findUserByIdentifier/{identifier}")
    UserVo findUserByIdentifier(@PathVariable("identifier") String identifier);


    /**
     * 根据角色查找菜单
     * @param role 角色
     * @return List
     */
    @GetMapping("/v1/menu/findMenuByRole/{role}")
    List<Menu> findMenuByRole(@PathVariable("role") String role);

    /**
     * 异步的日志事件记录
     * @param log
     */
    void saveLog(Log log);
}
