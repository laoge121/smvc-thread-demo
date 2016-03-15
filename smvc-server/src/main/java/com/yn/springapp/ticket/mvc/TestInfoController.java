package com.yn.springapp.ticket.mvc;

import com.yn.springapp.common.BaseController;
import com.yn.springapp.ticket.pojo.UserPojo;
import com.yn.springapp.ticket.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * User: pei.xu
 * Date: 15-1-29
 * Time: 下午4:40
 */
@Controller
@RequestMapping("/test")
public class TestInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/query.json")
    @ResponseBody
    public Map<String, Object> queryData() {
        System.out.println("请求正式处理!");
        UserPojo userPojo = new UserPojo();
        userPojo.setIdCard("371545655522521154");
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("data", userInfoService.queryUserInfo(userPojo));
        return retMap;
    }

    @RequestMapping("/saveInfo.json")
    @ResponseBody
    public Map<String, Object> saveUserInfo(UserPojo userPojo) {
        userInfoService.saveUserInfo(userPojo);
        return null;
    }
}
