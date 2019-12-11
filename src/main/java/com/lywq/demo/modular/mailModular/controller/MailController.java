package com.lywq.demo.modular.mailModular.controller;

import com.lywq.demo.common.constant.MailConstant;
import com.lywq.demo.common.modle.RetResponse;
import com.lywq.demo.common.modle.RetResult;
import com.lywq.demo.common.utils.ApplicationUtil;
import com.lywq.demo.modular.mailModular.model.Mail;
import com.lywq.demo.modular.mailModular.service.MailService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lywq WED
 * @title: MailController
 * @projectName demo
 * @description: 邮箱控制层
 * @date 2019/11/8 16:19
 */
@RestController
@RequestMapping("/mail")
@Api(value = "邮箱发送", tags = {"邮箱操作接口"}, description = "MailController")
public class MailController {

    @Resource
    private MailService mailService;

    /**
     * 发送注册验证码
     *
     * @param mail
     * @return 验证码
     * @throws Exception
     */
    @PostMapping("/sendTemplateMail")
    public RetResult<String> sendTemplateMail(Mail mail) throws Exception {
        String identifyingCode = ApplicationUtil.getNumStringRandom(6);
        mail.setSubject("欢迎注册柳意梧情");
        mail.setTemplateName(MailConstant.RETGISTEREMPLATE);
        Map<String, String> map = new HashMap<>();
        map.put("identifyingCode", identifyingCode);
        map.put("to", mail.getTo()[0]);
        mail.setTemplateModel(map);
        mailService.sendTemplateMail(mail);

        return RetResponse.makeOKRsp(identifyingCode);
    }

    @PostMapping("/sendAttachmentsMail")
    public RetResult<String> sendAttachmentsMail(Mail mail, HttpServletRequest request) throws Exception {
        mail.setSubject("测试附件");
        mailService.sendAttachmentsMail(mail, request);
        return RetResponse.makeOKRsp();
    }
}