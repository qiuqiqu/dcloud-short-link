package net.xdclass.controller;

import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.enums.BizCodeEnum;
import net.xdclass.enums.SendCodeEnum;
import net.xdclass.request.SendCodeRequest;
import net.xdclass.service.NotifyService;
import net.xdclass.util.CommonUtil;
import net.xdclass.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 *
 **/

@RestController
@RequestMapping("/api/account/v1")
@Slf4j
public class NotifyController {

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    private static final long CAPTCHA_CODE_EXPIRED = 1000 * 10 *  60;
    /**
     * 生成图形验证码
     *
     * @param request
     * @param response
     */
    @GetMapping("captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {

        String captchaText = captchaProducer.createText();
        log.info("验证码内容:{}", captchaText);

        //存储redis,配置过期时间
        redisTemplate.opsForValue().set(getCaptchaKey(request), captchaText,CAPTCHA_CODE_EXPIRED, TimeUnit.MILLISECONDS);

        BufferedImage bufferedImage = captchaProducer.createImage(captchaText);

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, "jpg", outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.error("获取流出错:{}", e.getMessage());
        }

    }

    /**
     * 发送短信验证码
     *
     * @return
     */
    @RequestMapping("send_code")
    public JsonData sendCode(@RequestBody SendCodeRequest sendCodeRequest,HttpServletRequest request) {

        //1. 验证图形验证码是否对
        String key = getCaptchaKey(request);
        String captchaKey = redisTemplate.opsForValue().get(key);
        if (captchaKey!=null&&captchaKey.equals(sendCodeRequest.getCaptcha())){
            //2. 删除缓存图形验证码
            redisTemplate.delete(key);
            //3. 发送短信验证码
           JsonData jsonData= notifyService.sendCode(SendCodeEnum.USER_REGISTER,sendCodeRequest.getTo());
           return jsonData;
        }else {
            return JsonData.buildResult(BizCodeEnum.CODE_CAPTCHA_ERROR);
        }

    }

    private String getCaptchaKey(HttpServletRequest request) {
        String IP = CommonUtil.getIpAddr(request);
        String userAgent = request.getHeader("User-Agent");
        String key="account-service:captcha:"+CommonUtil.MD5(IP+userAgent);
        log.info("redis 图形验证码 key:{}",key);
        return key;
    }


}
