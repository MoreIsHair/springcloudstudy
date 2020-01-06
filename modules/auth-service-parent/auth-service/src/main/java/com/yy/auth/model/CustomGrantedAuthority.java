package com.yy.auth.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author YY
 * @date 2020/1/6
 * @description
 */
public class CustomGrantedAuthority implements GrantedAuthority {


    private static final long serialVersionUID = -6643766279874218936L;

    private String roleName;

    public CustomGrantedAuthority(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
