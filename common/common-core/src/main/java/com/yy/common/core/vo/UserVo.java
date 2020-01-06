package com.yy.common.core.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author YY
 * @date 2020/1/6
 * @description
 */
@Data
public class UserVo {
    /**
     * 授权类型，1：用户名密码，2：手机号，3：邮箱，4：微信，5：QQ
     */
    private Integer identityType;

    /**
     * 唯一标识，如用户名、手机号
     */
    private String identifier;

    /**
     * 密码凭证，跟授权类型有关，如密码、第三方系统的token等
     */
    private String credential;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 头像对应的附件id
     */
    private String avatarId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 生日
     */
    private Date born;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 角色列表
     */
    private List<RoleVo> roleList;

    /**
     * 详细描述
     */
    private String userDesc;

    /**
     * 状态，0-启用，1-禁用
     */
    private Integer status;

    /**
     * 引导注册人
     */
    private String parentUid;

    /**
     * 乡/镇
     */
    private String streetId;

    /**
     * 县
     */
    private String countyId;

    /**
     * 城市
     */
    private String cityId;

    /**
     * 省份
     */
    private String provinceId;

    /**
     * 最近登录时间
     */
    private Date loginTime;

    /**
     * 用户归档时间
     */
    private Date lockTime;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 家庭角色，参考UserStudentConstant
     */
    private Integer familyRole;
}
