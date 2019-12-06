package com.lywq.demo.modular.uploadModular.controller;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.lywq.demo.common.modle.RetResponse;
import com.lywq.demo.common.modle.RetResult;
import com.lywq.demo.common.utils.OSSClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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

    @ApiOperation(value = "文件上传", notes = "文件上传")
    @PostMapping(value = "/uploadFile")
    public RetResult<String> uploadFile(@RequestParam("file") MultipartFile file) {
        // 获取实例
        OSSClient ossClient = OSSClientUtil.getOSSClient();
        // 上传文件
        String uploadImgOSS = OSSClientUtil.uploadFileToOSS(ossClient, file);
        // url地址
        String privateUrl = OSSClientUtil.getPrivateUrl(ossClient, uploadImgOSS);
        return RetResponse.makeOKRsp(privateUrl);
    }

    @ApiOperation(value = "创建文件夹", notes = "创建文件夹")
    @PostMapping(value = "/mkdir")
    public RetResult<String> mkdir(String bucketName, String folder){
        // 获取实例
        OSSClient ossClient = OSSClientUtil.getOSSClient();
        // 创建文件夹
        String fileDir = OSSClientUtil.createFolder(ossClient,bucketName,folder);
        return RetResponse.makeOKRsp(fileDir);
    }

    @ApiOperation(value = "删除文件", notes = "删除文件")
    @GetMapping(value = "deleteFile")
    public RetResult<String> deleteFile(String fileName){
        // 获取实例
        OSSClient ossClient = OSSClientUtil.getOSSClient();
        OSSClientUtil.delete(ossClient,fileName);
        return RetResponse.makeOKRsp("删除成功");
    }

//    @ApiOperation(value = "下载文件", notes = "下载文件")
//    @GetMapping(value = "downLoadFile")
//    public RetResult<String> downLoadFile(String fileName){
//        // 获取实例
//        OSSClient ossClient = OSSClientUtil.getOSSClient();
//        OSSObject object = OSSClientUtil.downLoadFile(ossClient,fileName);
//        System.out.println(object);
//        return RetResponse.makeOKRsp("下载成功");
//    }

    @ApiOperation(value = "下载文件", notes = "下载文件")
    @GetMapping("download")
    public RetResult<String> download(@RequestParam("fileName") String fileName, HttpServletResponse response) throws IOException {
        // 获取实例
        OSSClient ossClient = OSSClientUtil.getOSSClient();
        //通知浏览器以附件形式下载
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String(fileName.getBytes(), "ISO-8859-1"));
        OSSClientUtil.exportOssFile(ossClient,response.getOutputStream(),fileName);
        return RetResponse.makeOKRsp("下载成功");
    }

}
