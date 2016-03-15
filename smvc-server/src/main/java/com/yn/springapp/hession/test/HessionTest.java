package com.yn.springapp.hession.test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.yn.springapp.hession.data.UserInfoService;
import com.yn.springapp.hession.data.UserPojo;
import com.yn.springapp.hession.simple.SimpleService;
import proxy.jdk.User;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

/**
 * User: pei.xu
 * Date: 15-7-4
 * Time: 下午8:22
 */
public class HessionTest {

    public static void main(String[] args) throws MalformedURLException, InvocationTargetException, IllegalAccessException {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
//
//        UserInfoService userInfoService = (UserInfoService) hessianProxyFactory.create(UserInfoService.class, "http://localhost:8080/remoting/getUserInfo");
//
//        System.out.println("<<<" + userInfoService.getUserInfo());


        SimpleService simple = (SimpleService) hessianProxyFactory.create(SimpleService.class, "http://localhost:8080/remoting/invoke");

        System.out.println("<<<" + simple.getData());
        System.out.println(simple.invoke("userInfoService", "getUserInfo", new Object[]{}));
        System.out.println(simple.invoke("userInfoService", "getUserInfo", new Object[]{"你好"}));
        System.out.println(simple.invoke("userInfoService", "getUserInfo", new Object[]{new UserPojo("xp", "111111")}));
    }
}
