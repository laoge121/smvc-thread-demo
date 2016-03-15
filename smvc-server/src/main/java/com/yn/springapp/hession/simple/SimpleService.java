package com.yn.springapp.hession.simple;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by pei.xu on 2016/3/11.
 */
public interface SimpleService {

    /**
     * 具体业务实现
     *
     * @param path   请求类路径
     * @param method 方法名称
     * @return
     */
    public Object invoke(String path, String method, Object args[]) throws InvocationTargetException, IllegalAccessException;

    public String getData();
}
