package com.lywq.demo.modular.alipayModular.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.lywq.demo.common.modle.RetResponse;
import com.lywq.demo.common.modle.RetResult;
import com.lywq.demo.common.utils.AliPayUtil;
import com.lywq.demo.config.AlipayConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @author 王恩典
 * @title: PayController
 * @projectName demo
 * @description: alipay控制层
 * @date 2019/12/7 15:39
 */
@Slf4j
@Api(value = "支付宝支付", tags = {"alipay操作接口"}, description = "PayController")
@RequestMapping("/alipay")
@RestController
public class AliPayController {

    /**
     * 调用支付
     *
     * @param WIDout_trade_no
     * @param WIDtotal_amount
     * @param WIDsubject
     * @param WIDbody
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/pay")
    @ApiOperation(value = "支付", tags = {"alipay操作接口"}, notes = "支付")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "WIDout_trade_no", value = "商户订单号", required = true),
            @ApiImplicitParam(name = "WIDtotal_amount", value = "付款金额", required = true),
            @ApiImplicitParam(name = "WIDsubject", value = "订单名称", required = true),
            @ApiImplicitParam(name = "WIDbody", value = "商品描述", required = false)
    })
    public String payController(@RequestParam("WIDout_trade_no") String WIDout_trade_no,
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
        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "10m";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"timeout_express\":\"" + timeout_express + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

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

        return form;
    }

    /**
     * 同步回调
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     * @throws AlipayApiException
     */
    @RequestMapping("/return_url")
    @ApiOperation(value = "同步回调", tags = {"alipay操作接口"}, notes = "同步回调")
    public RetResult<String> return_url(HttpServletResponse response, HttpServletRequest request) throws IOException, AlipayApiException {
        log.info(">>>>>>>>支付成功, 进入同步通知接口...");
        boolean verifyResult = AliPayUtil.rsaCheckV1(request);
        if (verifyResult) {
            //商户订单号
            String out_trade_no = AliPayUtil.getByte(request.getParameter("out_trade_no"));
            //支付宝交易号
            String trade_no = AliPayUtil.getByte(request.getParameter("trade_no"));
            log.info("商户订单号：{}，支付宝交易号，{}", out_trade_no, trade_no);
            return RetResponse.makeOKRsp("paySuccess");
        } else {
            return RetResponse.makeErrRsp("payFail");
        }
    }

    /**
     * 异步回调
     *
     * @param response
     * @param request
     * @return
     * @throws IOException
     * @throws AlipayApiException
     */
    @RequestMapping("/notify_url")
    @ApiOperation(value = "同步回调", tags = {"alipay操作接口"}, notes = "同步回调")
    public String notify_url(HttpServletResponse response, HttpServletRequest request) throws IOException, AlipayApiException {
        log.info(">>>>>>>>支付成功, 进入异步通知接口...");
        // 一定要验签，防止黑客篡改参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder notifyBuild = new StringBuilder(">>>>>>>>>> alipay notify >>>>>>>>>>>>>>\n");
        parameterMap.forEach((key, value) -> notifyBuild.append(key + "=" + value[0] + "\n"));
        notifyBuild.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info(notifyBuild.toString());
        boolean flag = AliPayUtil.rsaCheckV1(request);
        if (flag) {
            /**
             * TODO 需要严格按照如下描述校验通知数据的正确性
             *
             * 商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
             * 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
             * 同时需要校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
             *
             * 上述有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
             * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
             * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
             */

            //交易状态
            String tradeStatus = AliPayUtil.getByte(request.getParameter("trade_status"));
            // 商户订单号
            String out_trade_no = AliPayUtil.getByte(request.getParameter("out_trade_no"));
            //支付宝交易号
            String trade_no = AliPayUtil.getByte(request.getParameter("trade_no"));
            //付款金额
            String total_amount = AliPayUtil.getByte(request.getParameter("total_amount"));
            log.info("交易状态:{},商户订单号:{},支付宝交易号:{},付款金额:{}", tradeStatus, out_trade_no, trade_no, total_amount);
            // TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
            // TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
            if (tradeStatus.equals("TRADE_FINISHED")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                //如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            } else if (tradeStatus.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。

            }
            return "success";
        }
        return "fail";
    }

    /**
     * 查看支付流水
     *
     * @param orderId
     * @return
     * @throws IOException
     * @throws AlipayApiException
     */
    @RequestMapping(value = "/queryPay")
    @ApiOperation(value = "查看支付流水", tags = {"alipay操作接口"}, notes = "查看支付流水")
    public String queryPay(String orderId) throws IOException, AlipayApiException {
        AlipayClient alipayClient = AliPayUtil.alipayClient;
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(orderId);
        //设置请求参数
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        alipayRequest.setBizModel(model);
        //请求
        String result = alipayClient.execute(alipayRequest).getBody();
        return result;
    }

    /**
     * 退款
     *
     * @param orderNo 商户订单号
     * @return
     */
    @RequestMapping("/refund")
    @ApiOperation(value = "退款", tags = {"alipay操作接口"}, notes = "退款")
    public String refund(String orderNo) throws AlipayApiException {
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        // 商户订单号
        model.setOutTradeNo(orderNo);
        // 退款金额
        model.setRefundAmount("0.1");
        // 退款原因
        model.setRefundReason("无理由退货");
        // 退款订单号(同一个订单可以分多次部分退款，当分多次时必传)
        String outOrderId = UUID.randomUUID().toString();
        model.setOutRequestNo(outOrderId);
        log.info("退款请求号：{}", outOrderId);
        alipayRequest.setBizModel(model);
        AlipayTradeRefundResponse alipayResponse = AliPayUtil.alipayClient.execute(alipayRequest);
        return alipayResponse.getBody();
    }

    /**
     * 退款查询
     *
     * @param orderNo       商户订单号
     * @param refundOrderNo 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部订单号
     * @return
     * @throws AlipayApiException
     */
    @RequestMapping("/refundQuery")
    @ApiOperation(value = "退款查询", tags = {"alipay操作接口"}, notes = "退款查询")
    public String refundQuery(String orderNo, String refundOrderNo) throws AlipayApiException {
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();

        AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
        model.setOutTradeNo(orderNo);
        model.setOutRequestNo(refundOrderNo);
        alipayRequest.setBizModel(model);
        AlipayTradeFastpayRefundQueryResponse alipayResponse = AliPayUtil.alipayClient.execute(alipayRequest);
        System.out.println(alipayResponse.getBody());

        return alipayResponse.getBody();
    }

    /**
     * 关闭交易
     *
     * @param orderNo
     * @return
     * @throws AlipayApiException
     */
    @RequestMapping("/close")
    @ApiOperation(value = "关闭交易", tags = {"alipay操作接口"}, notes = "关闭交易")
    public String close(String orderNo) throws AlipayApiException {
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        AlipayTradeCloseModel model = new AlipayTradeCloseModel();
        model.setOutTradeNo(orderNo);
        alipayRequest.setBizModel(model);
        AlipayTradeCloseResponse alipayResponse = AliPayUtil.alipayClient.execute(alipayRequest);
        System.out.println(alipayResponse.getBody());
        return alipayResponse.getBody();
    }

}