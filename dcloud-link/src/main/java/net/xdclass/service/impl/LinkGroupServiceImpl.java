package net.xdclass.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.controller.request.LinkGroupAddRequest;
import net.xdclass.interceptor.LoginInterceptor;
import net.xdclass.manager.LinkGroupManager;
import net.xdclass.model.LinkGroupDO;
import net.xdclass.service.LinkGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LinkGroupServiceImpl implements LinkGroupService {
    @Autowired
    LinkGroupManager linkGroupManager;

    /**
     * 新增分组
     * @param linkGroupAddRequest
     * @return
     */
    @Override
    public int add(LinkGroupAddRequest linkGroupAddRequest) {
        Long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();


        LinkGroupDO linkGroupDO = new LinkGroupDO();
        linkGroupDO.setTitle(linkGroupAddRequest.getTitle());
        linkGroupDO.setAccountNo(accountNo);

        int rows = linkGroupManager.add(linkGroupDO);

        return rows;
    }

    /**
     * 删除分组
     * @param groupId
     * @return
     */
    @Override
    public int del(Long groupId) {

        Long accountNo = LoginInterceptor.threadLocal.get().getAccountNo();

        return linkGroupManager.del(groupId, accountNo);

    }
}
