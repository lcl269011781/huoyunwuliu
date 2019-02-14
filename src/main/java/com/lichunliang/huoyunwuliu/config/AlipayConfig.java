package com.lichunliang.huoyunwuliu.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092300580892";

    // 商户私钥，您的PKCS8格式RSA2私钥
//    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDT23cDQRWv0ODCCJvH9oAVfkcg2MlFSal9OXs9P66bzwXbc3upskkX1XHv5FKiRU03hF47KZxFCU5pwS5Pd1kd8AT4qfr1sCnBkswbjPDcVWM+83sAhKhfGj3miG1l40xwse+btnuXSD1GhLxnG+zyUrcbyDF/ddbhhAUAYchjKD59sXbA808ZYF1RAsNaDwJsuecfSoUNf50vhXZr69Zk0KUPELkVyPOeB8Tb/uB8IPwwuQgM5vWk6ShICITEO9SXwGNMLDQFuMtSr/b4XcO7LCfaM6C7VNC7JiYICUokWtl9MTuM4oKhd/hIwXK360HvgGwBph48lHY+ElIQjC4FAgMBAAECggEAYZLqtWz8eAVT/wnH9cjqJPwL9mziBi9vCGW5BPSXjttCb1g41GdwkrGT0FDwXhxc+/zTCZyYhJNMshhvI9psCQXKxFxFYXLJSIY7y1SWemQ5WXz6Ybq3cVW8vLiI6ixNwHmzSZw6P+rdjeUEgNizwMen3fvOEE5NsYAEbbCbgk7oMG24BI7DSbtRlbdP3a4agz3J9Tl7AT1TpbrLQqCPo4C/aLr5DqGvZuzdWvhVSwcCDZCCkm4D9aiQf2aDXDiZDT6ZsoaKXQ6PUuJ39ukilaw1E1Sd8Ai0el/AAPzGUQoKq4TeYQTTdW160vvIsS6dDpcIVy2Euq23/AwHPw4oAQKBgQDriicWZIgmQAtNvSrXIGsIqmBgp6wRJPsJ3TXQnLBcUfmq/OOGvycmhFB7U4C/ZkZyLcLT1FZPyHe6kY8N7Pi1z8oYuaAGcPNQz91Mxx51dNjTJ/acjtLKlGbzEe+MVamAifHCpPSS6Sm9QLrFe0h9BmDnPdilNVOX32oQKHWGQQKBgQDmQqwGx23f+Sp9je7PEy++/T3MU+aCEOLYo4qZ9SDcOATAUVM+4IyDRh8yq0BzbWmGYHAhVWWCNVtTLvxBsNNCA4mr7as2Taf+iS+HK3h9tlthujr4VBOGbjm3iPTWLyxUOKm1ySp9K03jos4O5kSbi6iEk5Q93GtAq5DSMtBexQKBgEk7MtvrdckusFUDM4afNagEEOL8Pqs17Dcs58ZscQjDE/Ttm3ARjoKTMNKnK6hWjCCDycdChXNYyKDhELErtEgSyqrVS8O06nF9rzhn0DiEk8VS74HNhgwmB7wbH3dqSsEEIn55+vCSPb/xmCA+LFNy4D4FJI54/fyCId/UNHYBAoGBAOBhAe+NMQW7cWtt/tNtF+7eKfVNoOcH+3YuT6bdVwshz4d0+FDiUiquD0K53JFegiE/IqVo6QTsZ7T0qT5+SC8g8WxHqrq9C8Ke/mCSvNSL+hqOdl5815X9HWM7I5L/3wunN8F8aVoV882qXW/pddu5SRsAEMoDW8RmKzaZ67EdAoGAey7W+/oEJQnJ1AlaMJfnC0eY2CWoE/8RRYjsKqzw1gP9nKEFM1Dy+FtUqqPoYRhDX7JaJvn6gJK7hmftvlbnw/BWsiyKX+GhobRYs7y/4Cs7Qini0xMQZuwdl3VzQ86HVoUBph/+RJWSRj1EJkOeHF2FQRpbNO0+VWz0UZ/W2Jw=";
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCuPXphFwXHF20w\n" +
            "sJz9OmnoPiRAbYQX2vBA9WGzkIWBvJQQA/1Z0JgMM3Oh2JqnJjy+ezyIaCIl00LA\n" +
            "QmPBFHHg6VI1YgcvFWb4wFdVrmAOjHsr6UiSWRGaB2ZfFeHlDZp6mATa4DA1bDL7\n" +
            "5g5/Kdzb/pIYinIabH6eTSOgybYqVeNIUAcijUgVvSwyHexbCDlIPwMJ6zLEXy1K\n" +
            "f2Sge7+k7Mtp0XMo69qfvhcHNBAs28GNQU4d1CVfhyOQ3j28lqkZxS3LluMh4MTs\n" +
            "uQipMCpUu407jIZorjMFHc6MrG338rru+qTfc2a/dyZr15YKw+5+O2fMlSCBUG3n\n" +
            "HIjnFenzAgMBAAECggEBAKEGI9ESS76ggcJbldnYYAPHWg1IqKi7JwmBPFeMGjW5\n" +
            "p0PdzAdy5PFxo01RNit/BOgsT9boFZbLVIcFU9ceI9ftCJJZE7aJmu4MIyoFyrPn\n" +
            "U/VS1ESbl863Q/5f5LGgDsF5OBNm2Gampb9x7rfKYlfVPgL7/1M4LRPyyhhQEc+w\n" +
            "pa6oeWTISSOlzvtTmDR+PFwgf1I0QyI9elH4lLlUFOV7sTvFWLt91uptGrLGIiR+\n" +
            "6pbKyAn14A9MIz3uqkoVqhaGBWtbWdAh/ff8T2OtNSHTZq79sWtBSwJEKd9Ulnjv\n" +
            "3NHjdjMA4Mt5qqfUx8q8sSDS0rANGd8ELh3+dkfF98ECgYEA2FANVAT9wazu9hI4\n" +
            "6qdt4jbWT19JRJKUqZy/7pQE+czOG0QXhBIIEctfRKOBznnxqLSuV4DSJwzOJw4w\n" +
            "IAMwv7EyZ5hzSX8vLyilgLpIeYrG0gdIs8uCTkf23JwhN6IsEUzfBUquyk+Pid+3\n" +
            "Czt9hD8eFbRJpIJhdwbtBlJhf9ECgYEAzjVUH07A4J4vmBairF7lZVpgceuGJ54a\n" +
            "s+Z54poB5qmy4F5wYIzH9kZiRCIK0AMrF5RFkTWals3SqoTRvRRiXnTBJp2VWL96\n" +
            "no7IoFsOxswzz04dSh5J3mreT68ATcDmAnKbIwyipTo0obB0Xp3JJFIM/7YDq+Sz\n" +
            "pjGmfkBr4oMCgYAvJWOCSIQ2TkbXAym8mzlr8J/NNOt3MquMPEno6gIbn/PcJX8q\n" +
            "nfN8TWM6RpjoM9Pj/V1yXPVw5W2ZPZJnp8pcPXbK9YNLJZlXIfjQkclyFDk5VbUW\n" +
            "2uhPv4eilbT3tzT7oFGLibeHCMg0OLpgjFrTFB6uhFj4riIoGy5WDmaJwQKBgAg3\n" +
            "IbHZnl+UjcreY/7ADR2bmyoOuxqK9v9h2EtJ3XeGHUVJHesK0NCj/zg9rvaYmbfn\n" +
            "xVnCq/UEIDeA8SY6UKz4s9VJA/E0N7tnbuDMn+sVqZ7BuvL//+tnkJHWJAn0/HTu\n" +
            "gfXEhBpXFk+2yvwWBzR2PGRXqQ/JHcsqQTrYcDPnAoGAYarexHfKG0MTGKGSfW/Y\n" +
            "HmmEQgYN4rfpMTbuxtjkwGbgClxU5oxHswiydgcEMh2Qr7Ierk7W3vYUqjVRbRlJ\n" +
            "4VtaLXbP1TCM4k1H0t9cALQeFCtY8/P6rq10/WG+Ua9k/LtJsvjleFzIDtNom3OJ\n" +
            "IzXAa5QB9QgXokyC5DR89oA=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
//    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2wjs4psQfj/OBqE2UOTwE+ZC3Y2BwZXxNOrOTUF3TqjqLpQT/V3CVUEq9p8FLe5bmccmNOmHDo28yWwKsCj1Kawtg+1zIrF0ETy7DzHg0TqxKKQkLAf7DfGn/Zs47yj4K3FN6BA04JTBM7/+5eKfDLmy6/OYAiDiC4D2Z4LnSaupzR4qsDI3Nhn7JT9VwcGHZLayRCeyyjYn/i/BJu4oj3kMR82D+jxvnmUuufX9BE3VNq0ZuOI1v/iE5jshbEcyHHBo5rer9nQ4LYAQdL1s6IMXlAGSQ6Ah7gJ21YSJ3KGQJL6xfA9FMXow2sFcBJw1L6vOW22m4q20kqGfTe18wQIDAQAB";
    public static String alipay_public_key="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqvCwsbF8/KvCfK1RSREKpC26GnQURTjo97d112vnwx5IJJfyDkcHfTQQ3yTF1AvwsjnC9CH50LSPExyctH4oualZG5mArTWJ09tyQh6Khz6E1GJXWDao3g8we2K0onxXprLzzJm+nrkPH0pm8V4f8EM6h6XPTFmz5dm3JI2dvUCuj7Oz2SAwGYqVj5WlVIbD2cuK+thVUlHeD+1XmsFCgcHQfircqHOtG5xSJyB2mbBw5gPqj8fGcEr9mH3q0yrAWo+8u2XM9qm1AziKEIJI6T5UjQxapcklTed3zlCStWleiONTCZFu6ca+xnu0+rXP8nISngJUw3rvS0Z6dA23KwIDAQAB\n";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://f2366532w2.iask.in/callback.html";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://f2366532w2.iask.in/return.html";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
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

