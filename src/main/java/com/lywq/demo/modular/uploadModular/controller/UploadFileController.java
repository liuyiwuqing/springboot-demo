package com.lywq.demo.modular.uploadModular.controller;

import com.lywq.demo.common.modle.RetResponse;
import com.lywq.demo.common.modle.RetResult;
import com.lywq.demo.common.utils.UploadActionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author 王恩典
 * @title: UploadFileController
 * @projectName demo
 * @description: 上传文件，支持多上传
 * @date 2019/11/7 11:12
 */
@RestController
@RequestMapping("/uploadFile")
@Api(tags = {"上传测试"}, description = "UploadFileController")
public class UploadFileController {

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传", notes = "支持多文件上传")
    public RetResult<List<String>> upload(HttpServletRequest httpServletRequest) throws Exception {
        List<String> list = UploadActionUtil.uploadFile(httpServletRequest);
        return RetResponse.makeOKRsp(list);
    }
}
