package com.yy.auth.security;

import com.yy.auth.model.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 * @author YY
 * @date 2020/1/3
 * @description 监听登录成功事件，记录登录信息
 */
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler {

    @EventListener({AuthenticationSuccessEvent.class, InteractiveAuthenticationSuccessEvent.class})
    public void processAuthenticationSuccessEvent(AbstractAuthenticationEvent event) {
        // 注意：登录包括oauth2客户端、用户名密码登录都会触发AuthenticationSuccessEvent，这里只记录用户名密码登录的日志
        if (event.getSource() instanceof OAuth2Authentication)
            return;
        if (event.getAuthentication().getPrincipal() instanceof CustomUserDetails) {
            log.info("CustomAuthenticationSuccessHandler->登录成功");
            // TODO 登陆成功的监听时间的实现
            // 获取ip、浏览器信息
            // 异步记录登录日志
            /**
             * 参考源码：
             * org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter
             * org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails
             */
        }
    }



    /**
     * 获取用户名
     *
     * @param authentication authentication
     * @return String
     */
    private String getUsername(Authentication authentication) {
        String username = "";
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else if (principal instanceof Principal) {
            username = ((Principal) principal).getName();
        }
        return username;
    }
}
