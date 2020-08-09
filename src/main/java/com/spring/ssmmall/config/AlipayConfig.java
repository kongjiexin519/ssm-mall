package com.spring.ssmmall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

@Configuration
public class AlipayConfig {

    public static String APP_ID;
    public static String MERCHANT_PRIVATE_KEY;
    public static String ALIPAY_PUBLIC_KEY;
    public static String SIGN_TYPE;
    public static String CHARSET;
    public static String GATEWAYURL;
    public static String notify_url;
    public static String return_url;
    public static String LOG_PATH;

    @Value("${alipay.app-id}")
    public void setAppId(String appId) {
        APP_ID = appId;
    }

    @Value("${alipay.private-key}")
    public void setMerchantPrivateKey(String merchantPrivateKey) {
        MERCHANT_PRIVATE_KEY = merchantPrivateKey;
    }

    @Value("${alipay.public-key}")
    public void setAlipayPublicKey(String alipayPublicKey) {
        ALIPAY_PUBLIC_KEY = alipayPublicKey;
    }

    @Value("${alipay.sign-type}")
    public void setSignType(String signType) {
        SIGN_TYPE = signType;
    }

    @Value("${alipay.charset}")
    public void setCHARSET(String CHARSET) {
        AlipayConfig.CHARSET = CHARSET;
    }

    @Value("${alipay.gateway-url}")
    public void setGATEWAYURL(String GATEWAYURL) {
        AlipayConfig.GATEWAYURL = GATEWAYURL;
    }

    @Value("${alipay.notify-url}")
    public static void setNotify_url(String notify_url) {
        AlipayConfig.notify_url = notify_url;
    }

    @Value("${alipay.return-url}")
    public static void setReturn_url(String return_url) {
        AlipayConfig.return_url = return_url;
    }

    @Value("${alipay.log-path}")
    public void setLogPath(String logPath) {
        LOG_PATH = logPath;
    }

    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(LOG_PATH + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (IOException e) {
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
