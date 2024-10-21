package net.xdclass.request;

import lombok.Data;

/**
 * 用户注册
 **/

@Data
public class AccountRegisterRequest {

    /**
     * 头像
     */
    private String headImg;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String pwd;



    /**
     * 邮箱
     */
    private String mail;

    /**
     * 用户名
     */
    private String username;


    /**
     * 短信验证码
     */
    private String code;

}
