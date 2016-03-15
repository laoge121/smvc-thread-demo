package com.yn.springapp.ticket.service;

import com.yn.springapp.ticket.pojo.UserPojo;

/**
 * User: pei.xu
 * Date: 15-2-2
 * Time: 下午12:10
 */
public interface UserInfoService {

    /**
     * 查询用户信息
     *
     * @param userPojo
     * @return
     */
    public UserPojo queryUserInfo(UserPojo userPojo);

    /**
     * 保存信息
     *
     * @param userPojo
     */
    public void saveUserInfo(UserPojo userPojo);

}
