package com.lywq.demo.modular.generatorModular.service.impl;

import com.lywq.demo.modular.generatorModular.dao.SystemLogMapper;
import com.lywq.demo.modular.generatorModular.model.SystemLog;
import com.lywq.demo.modular.generatorModular.service.SystemLogService;
import com.lywq.demo.common.base.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @Description: SystemLogService接口实现类
* @author lywq WED
* @date 2019/11/30 11:40
*/
@Service
public class SystemLogServiceImpl extends ServiceImpl<SystemLog> implements SystemLogService {

@Resource
private SystemLogMapper systemLogMapper;

}