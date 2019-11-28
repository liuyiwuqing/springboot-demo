package com.lywq.demo.modular.generatorModular.controller;

import com.lywq.demo.common.modle.RetResult;
import com.lywq.demo.common.modle.RetResponse;
import com.lywq.demo.common.utils.ApplicationUtils;
import com.lywq.demo.modular.generatorModular.model.SystemLog;
import com.lywq.demo.modular.generatorModular.service.SystemLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: SystemLogController类
* @author lywq WED
* @date 2019/11/28 09:26
*/
@RestController
@RequestMapping("/systemLog")
public class SystemLogController {

@Resource
private SystemLogService systemLogService;

@PostMapping("/insert")
public RetResult<Integer> insert(SystemLog systemLog) throws Exception{
    systemLog.setId(ApplicationUtils.getUUID());
    Integer state = systemLogService.insert(systemLog);
    return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = systemLogService.deleteById(id);
        return RetResponse.makeOKRsp(state);
        }

        @PostMapping("/update")
        public RetResult<Integer> update(SystemLog systemLog) throws Exception {
            Integer state = systemLogService.update(systemLog);
            return RetResponse.makeOKRsp(state);
            }

            @PostMapping("/selectById")
            public RetResult<SystemLog> selectById(@RequestParam String id) throws Exception {
            SystemLog systemLog = systemLogService.selectById(id);
            return RetResponse.makeOKRsp(systemLog);
            }

            /**
            * @Description: 分页查询
            * @param page 页码
            * @param size 每页条数
            * @Reutrn RetResult<PageInfo<SystemLog>>
            */
            @PostMapping("/list")
            public RetResult<PageInfo<SystemLog>> list(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "0") Integer size) throws Exception {
            PageHelper.startPage(page, size);
            List<SystemLog> list = systemLogService.selectAll();
            PageInfo<SystemLog> pageInfo = new PageInfo<SystemLog>(list);
            return RetResponse.makeOKRsp(pageInfo);
            }
            }