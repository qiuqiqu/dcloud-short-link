package net.xdclass.component;


import lombok.extern.slf4j.Slf4j;
import net.xdclass.config.SmsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import java.net.URLEncoder;


/**
 *  信息发送⼯具类封装
 **/

@Component
@Slf4j
public class SmsComponent {

    /**
     * 发送地址
     */
    private static final String URL_TEMPLATE = "https://wwsms.market.alicloudapi.com/send_sms";

    @Autowired
    private SmsConfig smsConfig;


    /**
     * 发送短信验证码
     * @param phoneNumber
     * @param templateId
     * @param code
     */
    public  void send(String phoneNumber, String templateId, String code) throws IOException, URISyntaxException, InterruptedException {

        String appCode = smsConfig.getAppCode();

        // 构建请求体
        String content = "code:" + URLEncoder.encode(code, StandardCharsets.UTF_8.toString());
        String body = "content=" + content + "&template_id=" + templateId + "&phone_number=" + phoneNumber;

        // 创建 HttpClient 实例
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        // 创建 HttpRequest 实例
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URL_TEMPLATE))
                .timeout(Duration.ofSeconds(10))
                .header("Authorization", "APPCODE " + appCode)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                .build();

        // 发送请求并获取响应
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // 打印响应状态码和响应体
        log.info("Response Code:{}",response.statusCode());
        log.info("Response Body:",response.body());
    }





}
