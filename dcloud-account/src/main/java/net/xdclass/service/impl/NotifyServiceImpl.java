package net.xdclass.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
