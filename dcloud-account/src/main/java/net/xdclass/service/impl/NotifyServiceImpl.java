package net.xdclass.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.component.SmsComponent;
import net.xdclass.config.SmsConfig;
import net.xdclass.enums.SendCodeEnum;
import net.xdclass.service.NotifyService;
import net.xdclass.util.CheckUtil;
import net.xdclass.util.CommonUtil;
import net.xdclass.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Version 1.0
 **/

@Service
@Slf4j
public class NotifyServiceImpl implements NotifyService {


    @Autowired
    private SmsComponent smsComponent;

    @Autowired
    private SmsConfig smsConfig;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public JsonData sendCode(SendCodeEnum sendCodeEnum, String to) {


        String code = CommonUtil.getRandomCode(6);

        if(CheckUtil.isEmail(to)){
            //发送邮箱验证码  TODO

        }else if(CheckUtil.isPhone(to)){

            //发送手机验证码
            smsComponent.send(to,smsConfig.getTemplateId(),code);

        }

        return JsonData.buildSuccess();
    }
}
