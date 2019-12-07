package com.lywq.demo.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author 王恩典
 * @title: StringUtil
 * @projectName demo
 * @description: 字符串操作工具
 * @date 2019/12/7 11:13
 */
public class StringUtil {
    /**
     * 截取字符串
     *
     * @param str   待截取的字符串
     * @param start 截取起始位置 （ 1 表示第一位 -1表示倒数第1位）
     * @param end   截取结束位置 （如上index）
     * @return
     */
    public static String sub(String str, int start, int end) {
        String result = null;

        if (str == null || str.equals(""))
            return "";

        int len = str.length();
        start = start < 0 ? len + start : start - 1;
        end = end < 0 ? len + end + 1 : end;

        return str.substring(start, end);
    }

    /**
     * 将字符串str的格式转为utf-8
     *
     * @param str
     * @return
     */
    public static String toUTF_8(String str) {
        String result = null;
        try {
            if (StringUtils.isEmpty(str)) {
                return str;
            }
            result = new String(str.getBytes("iso-8859-1"), "utf-8");
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 若str字符串以tag结束则剔除tag
     *
     * @param str 待剔除的字符串
     * @param tag 要剔除的标签
     * @return 剔除后的字符串
     * @throws Exception
     */
    public static String trimEnd(String str, String tag) throws Exception {
        String result = str;
        if (str == null || str.equals("")) {
            return str;
        }
        if (tag == null || tag.equals("")) {
            throw new Exception("参数tag 不能为null或‘’ ");
        }

        int tagPosition = str.lastIndexOf(tag);
        if (tagPosition + tag.length() == str.length()) {
            result = str.trim().substring(0, tagPosition);
        }
        return result;
    }

    /**
     * 截取指定最后一个字符后面的内容
     *
     * @param str 待截取的字符串
     * @param tag 指定的字符标签
     * @return
     */
    public static String subLastTag(String str, String tag) {
        String result = null;

        if (str == null || str.equals(""))
            return "";

        result = str.substring(str.lastIndexOf(tag) + 1);

        return result;
    }
}
