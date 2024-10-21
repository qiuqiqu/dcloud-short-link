package net.xdclass.service;

import net.xdclass.request.AccountLoginRequest;
import net.xdclass.request.AccountRegisterRequest;
import net.xdclass.util.JsonData;

public interface AccountService {
    /**
     * 用户注册
     * @param registerRequest
     * @return
     */
    JsonData register(AccountRegisterRequest registerRequest);

    JsonData login(AccountLoginRequest request);
}
