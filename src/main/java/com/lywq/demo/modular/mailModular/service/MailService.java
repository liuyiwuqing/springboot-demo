package com.lywq.demo.modular.mailModular.service;

import com.lywq.demo.modular.mailModular.model.Mail;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lywq WED
 * @title: MailService
 * @projectName demo
 * @description: 邮件业务类
 * @date 2019/11/8 16:13
 */
public interface MailService {

    /**
     * 发送简单邮件
     *
     * @param mail
     */
    void sendSimpleMail(Mail mail);

    /**
     * 发送带附件的邮件
     *
     * @param mail
     * @param request
     */
    void sendAttachmentsMail(Mail mail, HttpServletRequest request);

    /**
     * 发送静态资源  一张照片
     *
     * @param mail
     * @throws Exception
     */
    void sendInlineMail(Mail mail) throws Exception;

    /**
     * 发送模板邮件
     *
     * @param mail
     */
    void sendTemplateMail(Mail mail);
}
