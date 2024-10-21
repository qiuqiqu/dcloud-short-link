package net.xdclass.controller;


import net.xdclass.enums.BizCodeEnum;
import net.xdclass.request.AccountLoginRequest;
import net.xdclass.request.AccountRegisterRequest;
import net.xdclass.service.AccountService;
import net.xdclass.service.FileService;
import net.xdclass.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
@RestController
@RequestMapping("/api/account/v1")
public class AccountController {

    @Autowired
    private FileService fileService;

    @Autowired
    AccountService accountService;


    /**
     * 上传用户头像
     *
     * 默认最大是1M,超过则报错
     *
     * @param file 文件
     * @return
     */
    @PostMapping( "/upload")
    public JsonData uploadUserImg(@RequestPart("file") MultipartFile file){
        String result = fileService.uploadUserImg(file);
        return result!=null? JsonData.buildSuccess(result):JsonData.buildResult(BizCodeEnum.FILE_UPLOAD_USER_IMG_FAIL);
    }

    /**
     * 用户注册
     * @param registerRequest
     * @return
     */
    @PostMapping("register")
    public JsonData register(@RequestBody AccountRegisterRequest registerRequest){

        JsonData jsonData = accountService.register(registerRequest);
        return jsonData;
    }

    /**
     * 用户登录
     * @param request
     * @return
     */
    @PostMapping("login")
    public JsonData login(@RequestBody AccountLoginRequest request){

        JsonData jsonData = accountService.login(request);
        return jsonData;
    }

}

