package net.xdclass.manager;

import net.xdclass.model.AccountDO;

import java.util.List;

public interface AccountManager {
    int insert(AccountDO accountDO);

    /**
     * 根据手机号查询用户
     * @param phone
     * @return
     */
    List<AccountDO> findByPhone(String phone);
}
