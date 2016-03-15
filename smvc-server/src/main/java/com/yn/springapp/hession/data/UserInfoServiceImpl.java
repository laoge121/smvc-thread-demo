package com.yn.springapp.hession.data;

import com.alibaba.dubbo.config.annotation.Service;
import com.yn.springapp.jms.producer.producerService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * User: pei.xu
 * Date: 15-7-4
 * Time: 下午8:10
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private producerService producerService;

    @Override
    public Map<String, String> getUserInfo() {
        Map<String, String> retMap = new HashMap<String, String>();
        retMap.put("aa", "bbb");

        //发送mq消息
        //producerService.sendMessageQueueMessage("nihao");
        //producerService.sendMessageAwareMessage("hellow");
        // producerService.sendMessageAdapterMessage("呢呢");
        return retMap;

    }

    @Override
    public Map<String, String> getUserInfo(String data) {
        Map<String, String> retMap = new HashMap<String, String>();
        retMap.put("aa", data);

        return retMap;
    }

    @Override
    public Map<String, String> getUserInfo(UserPojo userPojo) {
        Map<String, String> retMap = new HashMap<String, String>();
        retMap.put("name", userPojo.getName());
        retMap.put("pwd", userPojo.getPwd());
        return retMap;
    }
}
