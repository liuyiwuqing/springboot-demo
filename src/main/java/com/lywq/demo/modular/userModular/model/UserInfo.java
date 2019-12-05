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
 * @description: 用户实体类
 * @date 2019/11/5 19:48
 */
@Data
@ApiModel(value="user对象",description="用户对象user")
public class UserInfo {
    /**
     * 主键
     */
    @Id
    @ApiModelProperty(value="用户id",name="id")
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名",name="userName",example="lywq")
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户密码
     */
    @ApiModelProperty(value="用户密码",name="password",example="123")
    private String password;

    /**
     * 加密盐值
     */
    @ApiModelProperty(value="加密盐值",name="salt")
    private String salt;

    /**
     * 用户所有角色值，用于shiro做角色权限的判断
     */
    @Transient
    @ApiModelProperty(value="用户所有角色值，用于shiro做角色权限的判断",name="roles")
    private Set<String> roles;

    /**
     * 用户所有权限值，用于shiro做资源权限的判断
     */
    @Transient
    @ApiModelProperty(value="用户所有权限值，用于shiro做资源权限的判断",name="perms")
    private Set<String> perms;

}
