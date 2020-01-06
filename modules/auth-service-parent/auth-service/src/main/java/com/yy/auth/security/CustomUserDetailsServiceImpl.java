package com.yy.auth.security;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.yy.auth.model.CustomGrantedAuthority;
import com.yy.auth.model.CustomUserDetails;
import com.yy.common.core.enums.LoginType;
import com.yy.common.core.properties.SysProperties;
import com.yy.common.core.vo.RoleVo;
import com.yy.common.core.vo.UserVo;
import com.yy.common.security.core.CustomUserDetailsService;
import com.yy.user.api.feign.UserServiceClient;
import com.yy.user.api.feign.constant.MenuConstant;
import com.yy.user.api.feign.module.Menu;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author YY
 * @date 2020/1/3
 * @description  从数据库获取用户信息
 */
@AllArgsConstructor
@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserServiceClient userServiceClient;

    private final SysProperties sysProperties;

    @Override
    public UserDetails loadUserByIdentifier(String username) throws UsernameNotFoundException {

        //  自定义用户的查找，返回自定义的springsecurity用户类
        long strart = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        UserVo userByIdentifier = userServiceClient.findUserByIdentifier(username);
        return new CustomUserDetails(strart, LoginType.PWD,username,userByIdentifier.getCredential(),getAuthority(userByIdentifier));
    }

    /**
     * 获取用户权限
     * @param userVo userVo
     * @return Set
     */
    private Set<GrantedAuthority> getAuthority(UserVo userVo) {
        // 权限集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 根据角色查找菜单权限
        List<Menu> menus = Lists.newArrayList();
        // 判断是否是管理员，是则查找所有菜单权限
        if (userVo.getIdentifier().equals(sysProperties.getAdminUser())) {
            // 查找所有菜单权限，因为角色一般是一个，这里只会执行一次
        } else {
            // 根据角色查询菜单权限
            List<RoleVo> roleList = userVo.getRoleList();
            if (CollUtil.isNotEmpty(roleList)) {
                for (RoleVo role : roleList) {
                    // 根据角色查找菜单权限
                    List<Menu> roleMenus = userServiceClient.findMenuByRole(role.getRoleCode());
                    if (CollUtil.isNotEmpty(roleMenus)){
                        menus.addAll(roleMenus);
                    }
                    // 权限如果前缀是ROLE_，security就会认为这是个角色信息，而不是权限，例如ROLE_ADMIN就是ADMIN角色，MENU:ADD就是MENU:ADD权限
                    authorities.add(new CustomGrantedAuthority(role.getRoleCode()));
                }
            }
        }
        if (CollUtil.isNotEmpty(menus)) {
            // 菜单权限
            List<GrantedAuthority> authorityList = menus.stream()
                    .filter(menu -> MenuConstant.MENU_TYPE_PERMISSION.equals(menu.getType()))
                    .map(menu -> new CustomGrantedAuthority(menu.getPermission())).collect(Collectors.toList());
            authorities.addAll(authorityList);
        }
        return authorities;
    }
}
