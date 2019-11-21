package com.lywq.demo.modular.userModular.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;


/**
 * @author 王恩典
 * @title: UserInfo
 * @projectName demo
 * @description: TODO
 * @date 2019/11/5 19:48
 */
@Data
@ApiModel("用户")
public class UserInfo {
    /**
     * 主键
     */
    @Id
    @ApiModelProperty("用户ID")
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户密码
     */
    @ApiModelProperty("用户密码")
    private String password;

    /**
     * 加密盐值
     */
    @ApiModelProperty("加密盐值")
    private String salt;

    /**
     * 用户所有角色值，用于shiro做角色权限的判断
     */
    @Transient
    @ApiModelProperty("用户所有角色值，用于shiro做角色权限的判断")
    private Set<String> roles;

    /**
     * 用户所有权限值，用于shiro做资源权限的判断
     */
    @Transient
    @ApiModelProperty("用户所有权限值，用于shiro做资源权限的判断")
    private Set<String> perms;

}
