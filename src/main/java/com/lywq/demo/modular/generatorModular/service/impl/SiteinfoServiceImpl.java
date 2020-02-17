package com.lywq.demo.modular.generatorModular.service.impl;

import com.lywq.demo.modular.generatorModular.dao.SiteinfoMapper;
import com.lywq.demo.modular.generatorModular.model.Siteinfo;
import com.lywq.demo.modular.generatorModular.service.SiteinfoService;
import com.lywq.demo.common.base.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @Description: SiteinfoService接口实现类
* @author lywq WED
* @date 2020/02/17 16:59
*/
@Service
public class SiteinfoServiceImpl extends ServiceImpl<Siteinfo> implements SiteinfoService {

@Resource
private SiteinfoMapper siteinfoMapper;

}