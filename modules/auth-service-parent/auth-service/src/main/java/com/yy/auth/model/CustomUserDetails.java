package com.yy.auth.model;

import com.yy.common.core.enums.LoginType;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author YY
 * @date 2020/1/3
 * @description 自定义的springsecurity的用户类（添加了一些属性）
 */
@Getter
public class CustomUserDetails extends User {


    private static final long serialVersionUID = -2746331215121615340L;
    /**
     * 登录类型
     */
    private LoginType loginType;

    /**
     * 开始授权时间
     */
    private long start;

    public CustomUserDetails(long start,LoginType loginType,String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.start = start;
        this.loginType = loginType;
    }

}
