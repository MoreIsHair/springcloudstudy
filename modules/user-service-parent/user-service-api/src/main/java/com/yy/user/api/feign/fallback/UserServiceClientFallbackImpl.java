package com.yy.user.api.feign.fallback;

import com.yy.common.core.vo.UserVo;
import com.yy.user.api.feign.UserServiceClient;
import com.yy.user.api.feign.module.Menu;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author YY
 * @date 2020/1/6
 * @description 用户服务断路器实现
 */
@Component
@Slf4j
public class UserServiceClientFallbackImpl implements UserServiceClient {

    @Setter
    private Throwable throwable;

    @Override
    public UserVo findUserByIdentifier(String identifier) {
        log.error("feign 查询用户信息失败: {}, {}",  identifier, throwable);
        return null;
    }

    @Override
    public List<Menu> findMenuByRole(String role) {
        log.error("feign 查询角色失败: {}, {}",  role, throwable);
        return null;
    }

}
