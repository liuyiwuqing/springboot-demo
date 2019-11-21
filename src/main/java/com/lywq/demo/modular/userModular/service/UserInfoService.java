package com.lywq.demo.modular.userModular.service;

import com.github.pagehelper.PageInfo;
import com.lywq.demo.common.base.Service;
import com.lywq.demo.modular.userModular.model.UserInfo;

/**
 * @author 王恩典
 * @title: UserInfoService
 * @projectName demo
 * @description: TODO
 * @date 2019/11/5 19:50
 */
public interface UserInfoService extends Service<UserInfo> {

    UserInfo selectById(String id);

    PageInfo<UserInfo> selectAll(Integer page, Integer size);

}
