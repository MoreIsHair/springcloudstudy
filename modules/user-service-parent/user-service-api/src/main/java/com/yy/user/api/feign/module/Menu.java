package com.yy.user.api.feign.module;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author YY
 * @date 2020/1/6
 * @description 菜单
 */
@Data
public class Menu {
    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    private String name;

    /**
     * 菜单权限标识
     */
    private String permission;

    /**
     * url
     */
    private String url;

    /**
     * 重定向url
     */
    private String redirect;

    /**
     * 父菜单ID
     */
    private String parentId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序号
     */
    private String sort;

    /**
     * 类型
     */
    private String type;

    /**
     * 模块
     */
    private String component;

    /**
     * 路径
     */
    private String path;

    /**
     * 备注
     */
    private String remark;
}
