package net.xdclass.request;

import lombok.Data;


@Data
public class SendCodeRequest {

    /**
     * 图形验证码
     */
    private String captcha;

    /**
     * 接收的手机号或者邮箱
     */
    private String to;
}
