package net.xdclass.service;

import net.xdclass.controller.request.LinkGroupAddRequest;

public interface LinkGroupService {
    /**
     * 新增分组
     * @param linkGroupAddRequest
     * @return
     */
    int add(LinkGroupAddRequest linkGroupAddRequest);

    /**
     * 删除分组
     * @param groupId
     * @return
     */
    int del(Long groupId);
}
