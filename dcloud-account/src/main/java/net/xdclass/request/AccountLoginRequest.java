package net.xdclass.request;

import lombok.Data;

/**
 * 用户登录
 **/

@Data
public class AccountLoginRequest {

    private String phone;

    private String pwd;
}
