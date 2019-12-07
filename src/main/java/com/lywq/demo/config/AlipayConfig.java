package com.lywq.demo.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 王恩典
 * @title: AlipayConfig
 * @projectName demo
 * @description: alipay配置
 * @date 2019/12/7 15:37
 */
public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2016101500695394";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCD2+40kcGhJoN6qpTrv2IQ6h2OPckZowCJm54dDsnHdWZhG2SH0UETKqIkQ8uyzUEvd+iEfHSiiTjDfUjoxnt+P6dU4BQqUE8AOEnjQDQeEXH8H3e0IJYEOswxLaTzkdjXhdlI0pg7M2oGFwPjLr4KQ6C3I/qwStMbNtFN6Y6F64FuafllYZQhRLx7X18WFIPZ47qJZ4W/mnw4KEC4flcXGrpaaJvnjhJygr64DHEoVKuJEMn8bvKP06QLeJiYrM28PuHUav4C4WB5e036sJz3kmgZBVv6vBY0tFPq+VcwXdu/5cS7v+dHAuW2PfPyVDeaSlRYVAjlCm75zgOTkRNPAgMBAAECggEAKMlu9XPgoSh4owkA56tbej/wxH74JaX2FJq+Xd6lK7103bTLc/eWWYdmgDE4YSZ8c2Jz2gdlTKGFnXKzis1ahcLKz8ZAdpZA2aCPumuxs1tSFtrk/CLYRJarcsrbqzwCByiv/Yam8CkHMrsLyMo8npAstnm6oa2Df3eFZsRIAcRVqtsUywwthLwJ23ge3KIuRlfbOeXRxY5+IcvN7rdczVhj3goRBwE20e9lq+Xrziog2huWkn2em55RYxk0IY3zGuYxZ6w8PvG++huC1wTmyizRXAMfitwakD1TLoeFeIllx5kAuKwU9C3DHREeLYwdYxWOssCsI7dIETKjObbhgQKBgQC4fYEGNa1XAKxx+HqIbVjKf3aU2ZXZyEbzNadTzUpcO4t0Ih3x6U3lZxLd86PQJW7FxUfOmaQdHLMlf7C01t//7J+gxbRIOf9CcPsEjTJQivm85YFFYWzw4U5IJhUBZixsd1Edxnwa1gDjtUh2pRb+JqOMwwFyLuNxcZoaR+ZPxQKBgQC29/jqn20114iMfM6vMux7nqR9WvGmkbDWMKXniFUsTQwgjpgP0FtHRYys5jUCJZN5AdwrGC6iNsCOsTqQYC69iP0Nj6caQ/VWqQAFtVN9vJA9LGM1vYgOVogmSlAuL9bvxq0yjg58oKhoGvhg1Egy/we9+13q6W4qe4pVcIPUAwKBgFX/+yM/5lvGLpy6LbRm9JsAq/BbNWVd3OUGv8dqzR0NjMZIGRlpniwvzjW0m0ouCOMcQYrnR/9iH4h9a4wBFk4gqZCyWIXKs1jvfbPzdd7hyeTqP0ZuUfA/ttQfHG+Yh2DQhfmRSszfsW50VSEpd/xlWpwFUyk8/j0lraZOmefZAoGBAIWXmuRcUVYNsAK7ogFj0i0UkQ+CFozDNXW+gXQDJAU7oE4pLzfkrrDkRCl90Fhn7TxeMk440tvX1FKbbqI5yAbVfcaoC59u3TRQItcIJRTaWAqCXVHAuBWX8sLaYZ7HiUz7KDyQYl12xF92ycGRXcH2WcXKQjGiDLDyEPf/NhHXAoGBAKGNpGW5hailwyRxs6iM3yFb7s9P+q/JGbPWZ4sg3bqS/2A6KG81XDfr/2HJCV1Z69xMcA7iZA3gMHdua+2SXmPJmR3nNs+Xxjh9ouLvxHCXBOjEkaCIHStuTJY6OZMxToCK5+3O/A5S38JREnhMNhAkk4iemP+2U7RkO3YMjG7B";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg9vuNJHBoSaDeqqU679iEOodjj3JGaMAiZueHQ7Jx3VmYRtkh9FBEyqiJEPLss1BL3fohHx0ook4w31I6MZ7fj+nVOAUKlBPADhJ40A0HhFx/B93tCCWBDrMMS2k85HY14XZSNKYOzNqBhcD4y6+CkOgtyP6sErTGzbRTemOheuBbmn5ZWGUIUS8e19fFhSD2eO6iWeFv5p8OChAuH5XFxq6Wmib544ScoK+uAxxKFSriRDJ/G7yj9OkC3iYmKzNvD7h1Gr+AuFgeXtN+rCc95JoGQVb+rwWNLRT6vlXMF3bv+XEu7/nRwLltj3z8lQ3mkpUWFQI5Qpu+c4Dk5ETTwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "https://item.jd.com/100008348572.html#none";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "https://item.jd.com/100008348572.html#none";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
