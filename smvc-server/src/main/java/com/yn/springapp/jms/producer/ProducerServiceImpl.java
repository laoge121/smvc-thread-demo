package com.yn.springapp.jms.producer;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

/**
 * User: pei.xu
 * Date: 15-7-5
 * Time: 上午8:46
 */
public class ProducerServiceImpl implements producerService, MessageListener {

    private JmsTemplate jmsTemplate;

    @Override
    public void sendMessageQueueMessage(final String message) {

        ActiveMQQueue destination1 = new ActiveMQQueue();
        destination1.setPhysicalName("queue");
        jmsTemplate.send(destination1, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("这是消息生产者将消息拼接" + message);
            }
        });
    }

    @Override
    public void sendMessageAwareMessage(final String message) {
        ActiveMQQueue destination1 = new ActiveMQQueue();
        destination1.setPhysicalName("awareMessage");
        jmsTemplate.send(destination1, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("这是消息生产者将消息拼接" + message);
            }
        });
    }

    @Override
    public void sendMessageAdapterMessage(final String message) {
        ActiveMQQueue destination1 = new ActiveMQQueue();
        destination1.setPhysicalName("adapterMessage");
        jmsTemplate.send(destination1, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("这是消息生产者将消息拼接" + message);
                textMessage.setJMSReplyTo(new ActiveMQQueue("responseMessage"));
                return textMessage;
            }
        });
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
