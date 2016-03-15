package com.yn.springapp.jms.producer;

import org.springframework.stereotype.Service;

/**
 * User: pei.xu
 * Date: 15-7-5
 * Time: 上午8:46
 */
@Service
public interface producerService {

    /**
     * 消息发送数据
     *
     * @param message
     */
    public void sendMessageQueueMessage(final String message);

    /**
     * 消息发送数据
     *
     * @param message
     */
    public void sendMessageAwareMessage(final String message);

    /**
     * 消息发送数据
     *
     * @param message
     */
    public void sendMessageAdapterMessage(final String message);
}
