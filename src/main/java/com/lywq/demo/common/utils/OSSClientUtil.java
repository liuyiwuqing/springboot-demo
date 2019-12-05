package com.lywq.demo.common.utils;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王恩典
 * @title: OSSClientUtil
 * @projectName demo
 * @description: OSS上传工具类
 * @date 2019/12/3 16:36
 */

@Configuration
@PropertySource("classpath:config/oss.properties")
public class OSSClientUtil {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(OSSClientUtil.class);

    /**
     * 阿里云API的密钥Access Key ID
     */
    private static String accessKeyId;
    /**
     * 阿里云API的密钥Access Key Secret
     */
    private static String accessKeySecret;
    /**
     * 阿里云API的内或外网域名
     */
    private static String endpoint;
    /**
     * 阿里云API的bucket名称
     */
    private static String bucketName;
    /**
     * 阿里云API的文件夹名称
     */
    private static String folder;
    /**
     * 阿里云API的文件夹名称 [券模块]
     */
    private static String couponFolder;
    /**
     * 阿里云API的文件夹名称 [图片子模块]
     */
    private static String imageFolder;
    /**
     * 阿里云API的文件夹名称 [视频子模块]
     */
    private static String videoFolder;
    /**
     * 阿里云API的文件夹名称 [文件子模块]
     */
    private static String textFolder;

    /**
     * 获取阿里云OSS客户端对象
     *
     * @return ossClient
     */
    public static OSSClient getOSSClient() {
        return new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 创建存储空间
     *
     * @param ossClient  OSS连接
     * @param bucketName 存储空间
     * @return
     */
    public static String createBucketName(OSSClient ossClient, String bucketName) {
        // 存储空间
        final String bucketNames = bucketName;
        if (!ossClient.doesBucketExist(bucketName)) {
            // 创建存储空间
            Bucket bucket = ossClient.createBucket(bucketName);
            logger.info("创建存储空间成功");
            return bucket.getName();
        }
        return bucketNames;
    }

    /**
     * 删除存储空间buckName
     *
     * @param ossClient  oss对象
     * @param bucketName 存储空间
     */
    public static void deleteBucket(OSSClient ossClient, String bucketName) {
        ossClient.deleteBucket(bucketName);
        logger.info("删除" + bucketName + "Bucket成功");
    }

    /**
     * 创建模拟文件夹
     *
     * @param ossClient  oss连接
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名如"liuyiwuqing/"
     * @return 文件夹名
     */
    public static String createFolder(OSSClient ossClient, String bucketName, String folder) {
        // 文件夹名
        final String keySuffixWithSlash = folder;
        // 判断文件夹是否存在，不存在则创建
        if (!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)) {
            // 创建文件夹
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            logger.info("创建文件夹成功");
            // 得到文件夹名
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
            String fileDir = object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

    /**
     * 根据key删除OSS服务器上的文件
     *
     * @param ossClient  oss连接
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名 如"liuyiwuqing/"
     * @param key        Bucket下的文件的路径名+文件名 如："upload/lywq.jpg"
     */
    public static void deleteFile(OSSClient ossClient, String bucketName, String folder, String key) {
        ossClient.deleteObject(bucketName, folder + key);
        logger.info("删除" + bucketName + "下的文件" + folder + key + "成功");
    }

    /**
     * 上传文件至OSS
     *
     * @param ossClient  oss连接
     * @param file       上传文件（文件全路径如：D:\\image\\lywq.jpg）
     * @param bucketName 存储空间
     * @param folder     模拟文件夹名 如"liuyiwuqing/"
     * @return String 返回的唯一MD5数字签名
     */
    public static String uploadObjectOSS(OSSClient ossClient, File file, String bucketName, String folder) {
        String resultStr = null;
        try {
            // 以输入流的形式上传文件
            InputStream is = new FileInputStream(file);
            // 文件名
            String fileName = file.getName();
            // 文件大小
            Long fileSize = file.length();
            // 创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            // 上传的文件的长度
            metadata.setContentLength(is.available());
            // 指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            // 指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            // 指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            // 如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            // 上传文件 (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, is, metadata);
            logger.info("上传文件返回结果：{}", JSON.toJSONString(putResult));
            // 解析结果
            resultStr = putResult.getETag();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }

    /**
     * 上传图片至OSS
     *
     * @param ossClient oss连接
     * @param file      上传文件（文件全路径如：D:\\image\\lywq.jpg）
     *                  存储空间
     *                  //     * @param folder
     *                  模拟文件夹名 如"image/"
     * @return String 返回的唯一MD5数字签名
     */
    public static String uploadImgToOSS(OSSClient ossClient, MultipartFile file) {
//		String resultStr = null;
        //String url = null;
        String downFileName = null;
        try {
            // 以输入流的形式上传文件
            InputStream is = file.getInputStream();
            // 文件名
            String fileName = file.getOriginalFilename();
            // 文件类型
            String type = getContentType(fileName);
            if (!type.equalsIgnoreCase("image")){
                return "上传类型不是图片";
            }
            // 文件大小
            Long fileSize = file.getSize();
            if (fileSize > 1024 * 1024 * 2) {
                return "上传文件大于2M";
            }
            // 创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            // 上传的文件的长度
            metadata.setContentLength(is.available());
            // 指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            // 指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            // 指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            // 如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            String time = df.format(new Date());
            StringBuilder StringBuilder = new StringBuilder();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            String prefix = fileName.substring(0, fileName.lastIndexOf("."));
            downFileName = StringBuilder.append(prefix).append("_").append(time).append(".").append(suffix).toString();
            metadata.setContentDisposition("filename/filesize=" + downFileName + "/" + fileSize + "Byte.");
            // 上传文件 (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, imageFolder + downFileName, is, metadata);
            logger.info("上传文件返回结果：{}", JSON.toJSONString(putResult));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return imageFolder + downFileName;
    }


    /**
     * 上传视频到oss
     *
     * @param ossClient
     * @param file
     * @return
     */
    public static String uploadVideoToOSS(OSSClient ossClient, MultipartFile file) {
        //String url = null;
        String downFileName = null;
        try {
            // 以输入流的形式上传文件
            InputStream is = file.getInputStream();
            // 文件名
            String fileName = file.getOriginalFilename();
            // 文件类型
            String type = getContentType(fileName);
            if (!type.equalsIgnoreCase("video")){
                return "上传类型不是视频";
            }
            // 文件大小
            Long fileSize = file.getSize();
            if (fileSize > 1024 * 1024 * 50) {
                return "上传文件大于50M";
            }
            // 创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            // 上传的文件的长度
            metadata.setContentLength(is.available());
            // 指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            // 指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            // 指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            // 如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            String time = df.format(new Date());
            StringBuilder StringBuilder = new StringBuilder();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            String prefix = fileName.substring(0, fileName.lastIndexOf("."));
            downFileName = StringBuilder.append(prefix).append("_").append(time).append(".").append(suffix).toString();
            metadata.setContentDisposition("filename/filesize=" + downFileName + "/" + fileSize + "Byte.");
            // 上传文件 (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, videoFolder + downFileName, is, metadata);
            logger.info("上传文件返回结果：{}", JSON.toJSONString(putResult));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return videoFolder + downFileName;
    }


    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static String getContentType(String fileName) {
        // 文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)
                || ".jpg".equalsIgnoreCase(fileExtension)
                || ".png".equalsIgnoreCase(fileExtension)
                || ".gif".equalsIgnoreCase(fileExtension)
                || ".jpeg".equalsIgnoreCase(fileExtension)) {
            return "image";
        }
        if (".mp4".equalsIgnoreCase(fileExtension)
                || ".flv".equalsIgnoreCase(fileExtension)
                || ".mp4".equalsIgnoreCase(fileExtension)
                || ".f4v".equalsIgnoreCase(fileExtension)
                || ".m3u8".equalsIgnoreCase(fileExtension)
                || ".webm".equalsIgnoreCase(fileExtension)
                || ".ogg".equalsIgnoreCase(fileExtension)) {
            return "video";
        }
        if (".txt".equalsIgnoreCase(fileExtension)
                ||".ppt".equalsIgnoreCase(fileExtension)
                || "pptx".equalsIgnoreCase(fileExtension)
                ||".doc".equalsIgnoreCase(fileExtension)
                || "docx".equalsIgnoreCase(fileExtension)
                || ".xml".equalsIgnoreCase(fileExtension)
                || ".vsd".equalsIgnoreCase(fileExtension)
                || ".html".equalsIgnoreCase(fileExtension)) {
            return "text";
        }
        // 默认返回类型
        return "";
    }

    /**
     * 获得私有url链接
     *
     * @param fileName
     * @return
     */
    @SuppressWarnings("unused")
    public static String getPrivateUrl(OSSClient ossClient, String fileName) {
        // 设置URL过期时间为100年 3600l* 1000*24*365*100
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 100);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, fileName, expiration);
        logger.info("获得url链接：{}", url.toString());
        if (url != null) {
            return url.toString();
        }
        return null;
    }

    /**
     * 获得共有url链接
     *
     * @param fileName
     * @return
     */
    @SuppressWarnings("unused")
    public static String getPublicUrl(OSSClient ossClient, String fileName) {
        // 设置URL过期时间为10年 3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(bucketName, fileName, expiration);
        logger.info("获得url链接：{}", url.toString());
        String[] split = url.toString().split("[?]");
        if (url != null) {
            return split[0];
        }
        return null;
    }


    /**
     * 下载图片
     *
     * @param fileName
     * @return
     */
    public static OSSObject downLoadImage(String fileName) {
        OSSClient ossClient = OSSClientUtil.getOSSClient();
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, fileName, HttpMethod.GET);
        // 设置过期时间。
        request.setExpiration(expiration);
        // 生成签名URL（HTTP GET请求）。
        URL signedUrl = ossClient.generatePresignedUrl(request);
        // 使用签名URL发送请求。
        Map<String, String> customHeaders = new HashMap<String, String>();
        // 添加GetObject请求头。
        //customHeaders.put("Range", "bytes=100-1000");
        OSSObject object = ossClient.getObject(signedUrl, customHeaders);
        return object;
    }


    @Value("${aliyun.accessKeyId}")
    public void setAccessKeyId(String accessKeyId) {
        OSSClientUtil.accessKeyId = accessKeyId;
    }

    @Value("${aliyun.accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret) {
        OSSClientUtil.accessKeySecret = accessKeySecret;
    }

    @Value("${aliyun.oss.endpoint}")
    public void setEndpoint(String endpoint) {
        OSSClientUtil.endpoint = endpoint;
    }

    @Value("${aliyun.oss.bucketName}")
    public void setBucketName(String bucketName) {
        OSSClientUtil.bucketName = bucketName;
    }

    @Value("${aliyun.oss.folder}")
    public void setFolder(String folder) {
        OSSClientUtil.folder = folder;
    }

    @Value("${aliyun.oss.subfolder.image.folder}")
    public static void setImageFolder(String imageFolder) {
        OSSClientUtil.imageFolder = imageFolder;
    }

    @Value("${aliyun.oss.subfolder.video.folder}")
    public static void setVideoFolder(String videoFolder) {
        OSSClientUtil.videoFolder = videoFolder;
    }

    @Value("${aliyun.oss.subfolder.text.folder}")
    public static void setTextFolder(String textFolder) {
        OSSClientUtil.textFolder = textFolder;
    }

    @Value("${aliyun.oss.coupon.image.folder}")
    public void setCouponFolder(String couponFolder) {
        OSSClientUtil.couponFolder = couponFolder;
    }


    public static String getAccessKeyId() {
        return accessKeyId;
    }

    public static String getAccessKeySecret() {
        return accessKeySecret;
    }

    public static String getEndpoint() {
        return endpoint;
    }

    public static String getBucketName() {
        return bucketName;
    }

    public static String getFolder() {
        return folder;
    }

    public static String getImageFolder() {
        return imageFolder;
    }

    public static String getVideoFolder() {
        return videoFolder;
    }

    public static String getTextFolder() {
        return textFolder;
    }

    public static String getCouponFolder() {
        return couponFolder;
    }

}