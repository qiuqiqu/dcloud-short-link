package net.xdclass.service;

import net.xdclass.enums.SendCodeEnum;
import net.xdclass.util.JsonData;

public interface NotifyService {
    /**
     * 短信验证码发送
     * @param userRegister
     * @param to
     * @return
     */
    JsonData sendCode(SendCodeEnum userRegister, String to);

    /**
     * 短信验证码校验逻辑
     * @param sendCodeEnum
     * @param to
     * @param code
     * @return
     */
    boolean checkCode(SendCodeEnum sendCodeEnum, String to, String code);
}

