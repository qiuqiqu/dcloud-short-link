package net.xdclass.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.service.NotifyService;
import net.xdclass.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Version 1.0
 **/

@Service
@Slf4j
public class NotifyServiceImpl implements NotifyService {


    @Autowired
    private RestTemplate restTemplate;


    @Override
    @Async("threadPoolTaskExecutor")
    public void testSend() {

//        try {
//            TimeUnit.MILLISECONDS.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        long beginTime = CommonUtil.getCurrentTimestamp();
        ResponseEntity<String> forEntity = restTemplate.getForEntity("https://map.baidu.com", String.class);
        String body = forEntity.getBody();
        long endTime = CommonUtil.getCurrentTimestamp();
        log.info("耗时={},body={}",endTime-beginTime,body);

    }
}
