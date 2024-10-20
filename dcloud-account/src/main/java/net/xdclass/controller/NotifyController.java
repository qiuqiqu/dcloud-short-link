package net.xdclass.controller;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.service.NotifyService;
import net.xdclass.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**

 **/

@RestController
@RequestMapping("/api/account/v1")
@Slf4j
public class NotifyController {


    @Autowired
    private NotifyService notifyService;

    /**
     * 测试发送验证码接口-主要是用于对比优化前后区别
     * @return
     */
    @RequestMapping("send_code")
    public JsonData sendCode(){
        notifyService.testSend();
        return JsonData.buildSuccess();
    }


//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Async
//    public void testSend() {
//        try {
//            TimeUnit.MILLISECONDS.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        long beginTime = CommonUtil.getCurrentTimestamp();
//        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://old.xdclass.net", String.class);
//        String body = forEntity.getBody();
//        long endTime = CommonUtil.getCurrentTimestamp();
//        log.info("耗时={},body={}",endTime-beginTime,body);
//
//    }



}
