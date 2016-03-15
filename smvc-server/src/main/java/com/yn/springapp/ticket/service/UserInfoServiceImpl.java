package com.yn.springapp.ticket.service;

import com.yn.springapp.ticket.dao.UserMapper;
import com.yn.springapp.ticket.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: pei.xu
 * Date: 15-2-2
 * Time: 下午12:11
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserMapper userMapping;

    @Override
    public UserPojo queryUserInfo(UserPojo userPojo) {
        System.out.println(">>>>>>>>>>>");
        return userMapping.queryUserInfoByIdCard(userPojo.getIdCard());
    }

    @Override
    public void saveUserInfo(UserPojo userPojo) {
        try {
            userMapping.saveUserInfo(userPojo);
        } catch (Exception e) {
            throw new RuntimeException("保存数据异常!");
        }
    }
}
