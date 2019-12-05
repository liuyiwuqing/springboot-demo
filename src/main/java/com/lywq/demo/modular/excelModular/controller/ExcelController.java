package com.lywq.demo.modular.excelModular.controller;

import com.lywq.demo.common.constant.ExcelConstant;
import com.lywq.demo.common.modle.RetResponse;
import com.lywq.demo.common.modle.RetResult;
import com.lywq.demo.common.utils.ExcelUtils;
import com.lywq.demo.modular.excelModular.model.ExcelData;
import com.lywq.demo.modular.userModular.model.UserInfo;
import com.lywq.demo.modular.userModular.service.UserInfoService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王恩典
 * @title: ExcelController
 * @projectName demo
 * @description: 导出excel表
 * @date 2019/11/8 19:48
 */
@RestController
@RequestMapping("excel")
@Api()
public class ExcelController {

    @Resource
    private UserInfoService userInfoService;

    @PostMapping("/test")
    public RetResult<Integer> test(){
        int rowIndex = 0;
        List<UserInfo> list = userInfoService.selectAll();
        ExcelData data = new ExcelData();
        data.setName("表格名称");
        List<String> titles = new ArrayList();
        titles.add("ID");
        titles.add("userName");
        titles.add("password");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        for(int i = 0, length = list.size();i<length;i++){
            UserInfo userInfo = list.get(i);
            List<Object> row = new ArrayList();
            row.add(userInfo.getId());
            row.add(userInfo.getUserName());
            row.add(userInfo.getPassword());
            rows.add(row);
        }
        data.setRows(rows);
        try{
            rowIndex = ExcelUtils.generateExcel(data, ExcelConstant.FILE_PATH + ExcelConstant.FILE_NAME);
        }catch (Exception e){
            e.printStackTrace();
        }
        return RetResponse.makeOKRsp(Integer.valueOf(rowIndex));
    }

    @GetMapping("/test2")
    public void test2(HttpServletResponse response){
        int rowIndex = 0;
        List<UserInfo> list = userInfoService.selectAll();
        ExcelData data = new ExcelData();
        data.setName("hello");
        List<String> titles = new ArrayList();
        titles.add("ID");
        titles.add("userName");
        titles.add("password");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        for(int i = 0, length = list.size();i<length;i++){
            UserInfo userInfo = list.get(i);
            List<Object> row = new ArrayList();
            row.add(userInfo.getId());
            row.add(userInfo.getUserName());
            row.add(userInfo.getPassword());
            rows.add(row);
        }
        data.setRows(rows);
        try{
            ExcelUtils.exportExcel(response,"test2",data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
