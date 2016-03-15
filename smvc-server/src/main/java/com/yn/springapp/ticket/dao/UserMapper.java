package com.yn.springapp.ticket.dao;

import com.yn.springapp.ticket.pojo.UserPojo;
import org.springframework.stereotype.Repository;

/**
 * User: pei.xu
 * Date: 15-2-2
 * Time: 上午11:24
 */
public interface UserMapper {

    /**
     * 查询用户信息
     *
     * @param idCard 证件号码
     * @return
     */
    public UserPojo queryUserInfoByIdCard(String idCard);

    /**
     * 保存用户信息
     *
     * @param userPojo
     */
    public void saveUserInfo(UserPojo userPojo);
}
