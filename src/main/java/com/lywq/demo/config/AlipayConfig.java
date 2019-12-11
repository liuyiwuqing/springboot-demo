package com.lywq.demo.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.lywq.demo.common.utils.AliPayUtil;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author lywq WED
 * @title: AlipayConfig
 * @projectName demo
 * @description: alipay配置
 * @date 2019/12/7 15:37
 */
public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2016101500695394";

    // 商户私钥，您的PKCS8格式RSA2私钥，生成的应用私钥
    public static String APP_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCw8nBPDnqQDS9n+PsE9YUB8LJBidFbPoWkOqWzVV36caWIx0F90PHVT2Tcg7bwGkeWjhPUIJ6XgRfhQ6kLzArtyg7nKLb280lNINHJ5u38rYsebgn1fio8XavJGWg+IaOuDRo+ydMm6vN9kkY85V/RPpmJmNIRKLqFESqAJuSXdHt0Kuq1TkLWfQdZMNorLplS6ll8Q3FX9L/bsYdEkF/dc8vjmjDw8tTLPXO4tWbB4MspsFH6wv7FLJZy6xkQJxbZSmtbSZ8gJFhX2aLjBUPaA04rYSPmzrqB6ttYtzYKFWNe1uNfrQok3RG6gP94ebHk7EoUIhOJeUFL85U8DFg7AgMBAAECggEBAIZCvoLN6qNozcKngdgGbzDEsIzYBLmmHAM72MTVDmTcXI0HG6NaqlR/VHmZm3JdjlonXdZEFfHBKxxqmVbxMI2zFlHBa+q/OKxELcpz5bom9/tT5UyXuXZqYNFK7PTP04FaQcLIouXRuoVaOt8z79s2E4v7H9GTRMt49fWdDS/5GC23me5yu16vtWekGQ5UtSFnABAIXktXNPg3gy4nLQeRFfbKXPMKxp8Y2hjJpVQLdEkjjvw7hQicpV+Y3Y6XMJUBDe9pTGJOFFoLYMP3wbwFTspgu0/wOybeE/myF8iLBOEa2mFpwWmHAJCCCju4etFDPhmaBAiHEqKYccnLx9ECgYEA2bOuguclkC+jOoT5ZfTEPji9VrtZZRL/oAckpvZYqsAInaDBCHw4NNNucELgOugQqp327ONIsG9wJblJzWKFCYYYnOCXFiuNxViB+HX04qCmLHbbnBx5aku4EN1tVXo0ifZSek0L/bnpIB3t10+w81bMggyKufPkiNJLsP4r9jMCgYEA0BNXO5kmj/TdZJ0vltYm9ah/8HJcuGnPbFLnKrUtjfG8g1BMcONFRoUwT9BRMu0Jgur+80nn8aMromiD5i0A8Ov/WFkv6b8/CZv1qDHzYcvyF9kgiqTktqNSWYM995dlREhJSs5AT7EaovImEy6Xu0mWSflPTIP+lJBP7yTnvdkCgYEAoCecqXaM3s784S80Hnp76F5rhCPdtwrL/ZljsGSohC0z6Wp6t9hSrSIiK/o25X8Vy77cCheGwS2qLkZqcesZfhEliDt9SvUNnfHn47Y7SoHu3eUu2GDmPXmo1tde+LQndJs+v/Rwbt9XDqaV84EP52SinMXHRNeU98blojbKdpsCgYEAg3e05jMd6lcMi0XFi8hDVXPhYkrfyqUQKaE37gQLhGP4d/+ETM9MNgJxQ/ybUVHzcISKEa3gnww5xaPDsus8bPyvmWLAoy/y5F9mF59355K0o9zMcs08tSj8HxKNVKKTmVES/fDTMYb1DX9yDyyhMfSMwLCFKhM8ByInXzIyOgECgYBeGvIn6e1xM6+CDhtce9XR+CIJi6yYsIg1epj7NKW1QAMHZ73p6QzBt/BN0yCnDn8nKnOfndk/Ex4CiNvzd6V1sWnvNvlqka+55DtpAabGPoYuLU3WZqF/tXp0QPOeiUo0a2rIUFcvkVGUArOWG7VMcPM2BmW6+9I05mBPMWGFQQ==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/appDaily.htm?tab=info 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyPgfRUHOspbw5tFe42PRox0qV0Gq2BARLcDW/qcQUUQipleS/C9AgQ1d2rKTSTADAuig0zCf5MVEOVwR0I+aE8IgLkZLPLD1lbdNnsGfGyVUhlsWyG1m4DXH65LwU2xBEMntm9zujxvnw6pfzfeYIOZSbFDcPpEVY6vzOqRcqV3cC0IOkBPqwoBXbm1u8PCKyQSfQeSL6N3x8rTk61EkIttQCQZi/vOgfdnm7bPQ/HhwBg5UMLJvZNDhsTZnJjo15Iotxyv+67K6/6Ku2TfxSlaJ8nJpL2x76JoM/zmBIGXF16+bjAfCjIDS/7d0GuB6PKfSmyoFHwuE/AlRQkiTYwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8888/alipay/notify_url";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:8888/alipay/return_url";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 日志地址
    public static String log_path = "C:\\";

    // 实例化客户端
    public static AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}
