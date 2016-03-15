package com.yn.springapp.jms.customer;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.*;

/**
 * SessionAwareMessageListener 测试
 * User: pei.xu
 * Date: 15-7-5
 * Time: 上午9:53
 */
public class CustomerSessionWareMessageListener implements SessionAwareMessageListener {

    public void onMessage(TextMessage textMessage, Session session) throws JMSException {
        System.out.println(textMessage.getText());

        ActiveMQQueue activeMQQueue = new ActiveMQQueue("awareMessageCallback");
        MessageProducer messageProducer = session.createProducer(activeMQQueue);
        TextMessage textMessage1 = session.createTextMessage("消息已收到");
        messageProducer.send(textMessage1);
    }

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        this.onMessage(message, session);
    }
}
