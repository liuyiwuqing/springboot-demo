package com.lywq.demo.modular.alipayModular.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lywq.demo.config.AlipayConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 王恩典
 * @title: PayController
 * @projectName demo
 * @description: alipay控制层
 * @date 2019/12/7 15:39
 */
@Api(value = "支付宝支付", tags = {"alipay操作接口"}, description = "PayController")
@RequestMapping("/alipay")
@RestController
public class AliPayController {

    @RequestMapping("/pay")
    @ApiOperation(value = "支付", tags = {"alipay操作接口"}, notes = "支付")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "WIDout_trade_no", value = "商户订单号", required = true),
            @ApiImplicitParam(name = "WIDtotal_amount", value = "付款金额", required = true),
            @ApiImplicitParam(name = "WIDsubject", value = "订单名称", required = true),
            @ApiImplicitParam(name = "WIDbody", value = "商品描述", required = false)
    })
    public void payController(@RequestParam("WIDout_trade_no") String WIDout_trade_no,
                              @RequestParam("WIDtotal_amount") String WIDtotal_amount,
                              @RequestParam("WIDsubject") String WIDsubject,
                              @RequestParam("WIDbody") String WIDbody,
                              HttpServletResponse response) throws IOException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(WIDout_trade_no.getBytes("ISO-8859-1"), "UTF-8");
        //付款金额，必填
        String total_amount = new String(WIDtotal_amount.getBytes("ISO-8859-1"), "UTF-8");
        //订单名称，必填
        String subject = new String(WIDsubject.getBytes("ISO-8859-1"), "UTF-8");
        //商品描述，可空
        String body = new String(WIDbody.getBytes("ISO-8859-1"), "UTF-8");

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

}