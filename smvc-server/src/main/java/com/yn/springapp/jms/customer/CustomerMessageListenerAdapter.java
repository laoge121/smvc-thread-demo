package com.yn.springapp.jms.customer;

import javax.jms.JMSException;

/**
 * User: pei.xu
 * Date: 15-7-5
 * Time: 上午10:29
 */
public class CustomerMessageListenerAdapter {

    public String handlerMessage(String message) throws JMSException {

        System.out.println("这是handlerMessage收到的消息" + message);

        return "消息已经收到了……………………";
    }

    public String executeMessage(String message) throws JMSException {

        System.out.println("这是executeMessage收到的消息" + message);

        return "消息已经收到了……………………";
    }
}
