package com.yy.common.security.core;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author YY
 * @date 2020/1/3
 * @description 自定义的获取用户接口（用来自定义CustomUserDetailsAuthenticationProvider获取用户）
 */
public interface CustomUserDetailsService {
    /**
     * 根据用户名和租户标识查询（需要把权限GrantedAuthority也查询出来set进入）
     * @param username   username
     */
    UserDetails loadUserByIdentifier(String username) throws UsernameNotFoundException;

}
