package com.lywq.demo.modular.uploadModular.controller;

import com.aliyun.oss.OSSClient;
import com.lywq.demo.common.modle.RetResponse;
import com.lywq.demo.common.modle.RetResult;
import com.lywq.demo.common.utils.OSSClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author 王恩典
 * @title: UploadOSSController
 * @projectName demo
 * @description: 上传文件到Oss
 * @date 2019/12/4 9:51
 */
@Api(value = "uploadOss", description = "上传Oss")
@RequestMapping("/uploadOss")
@RestController
public class UploadOSSController {

    @ApiOperation(value = "图片上传", notes = "图片上传")
    @PostMapping(value = "/uploadImg")
    public RetResult<String> uploadImg(@RequestParam("file") MultipartFile file) {
        // 获取实例
        OSSClient ossClient = OSSClientUtil.getOSSClient();
        // 上传图片
        String uploadImgOSS = OSSClientUtil.uploadImgToOSS(ossClient, file);
        // url地址
        String privateUrl = OSSClientUtil.getPrivateUrl(ossClient, uploadImgOSS);
        return RetResponse.makeOKRsp(privateUrl);
    }

    @ApiOperation(value = "视频上传", notes = "视频上传")
    @PostMapping(value = "/uploadVideo")
    public RetResult<String> uploadVideo(@RequestParam("file") MultipartFile file) {
        // 获取实例
        OSSClient ossClient = OSSClientUtil.getOSSClient();
        // 上传视频
        String uploadVideoOSS = OSSClientUtil.uploadVideoToOSS(ossClient, file);
        // url地址
        String privateUrl = OSSClientUtil.getPrivateUrl(ossClient, uploadVideoOSS);
        return RetResponse.makeOKRsp(privateUrl);
    }

    @ApiOperation(value = "创建文件夹", notes = "创建文件夹")
    @PostMapping(value = "/mkdir")
    public RetResult<String>  mkdir(String bucketName, String folder){
        // 获取实例
        OSSClient ossClient = OSSClientUtil.getOSSClient();
        // 创建文件夹
        String fileDir = OSSClientUtil.createFolder(ossClient,bucketName,folder);
        return RetResponse.makeOKRsp(fileDir);
    }


}
