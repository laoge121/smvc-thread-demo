package com.yn.springapp.hession.data;

import java.util.Map;

/**
 * User: pei.xu
 * Date: 15-7-4
 * Time: 下午8:10
 */
public interface UserInfoService {

    public Map<String, String> getUserInfo();

    public Map<String, String> getUserInfo(String data);

    public Map<String, String> getUserInfo(UserPojo userPojo);


}
