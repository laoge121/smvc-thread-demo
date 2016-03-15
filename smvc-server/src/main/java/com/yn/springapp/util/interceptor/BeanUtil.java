package com.yn.springapp.util.interceptor;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * User: pei.xu
 * Date: 15-2-2
 * Time: 下午5:22
 */
public class BeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取bean
     *
     * @param name 类名称
     * @return
     */
    public static Object getBean(String name) {

        if (null == applicationContext) {
            throw new RuntimeException("获取ApplicationContext异常 ApplicationContext 为空!");
        }

        return applicationContext.getBean(name);
    }
}
