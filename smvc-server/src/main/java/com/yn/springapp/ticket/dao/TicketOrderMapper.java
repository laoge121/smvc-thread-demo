package com.yn.springapp.ticket.dao;

import com.yn.springapp.ticket.pojo.TicketOrderPojo;
import org.springframework.stereotype.Service;

/**
 * 车票预订接口
 * User: pei.xu
 * Date: 15-2-3
 * Time: 下午6:46
 */
public interface TicketOrderMapper {

    /**
     * 查询指定的 车次信息
     *
     * @param ticketPojo
     * @return
     */
    public TicketOrderPojo queryTicketOrderByTrainNo(TicketOrderPojo ticketPojo);

    /**
     * 保存车票可用信息
     *
     * @param ticketPojo
     */
    public void saveTicketOrderInfo(TicketOrderPojo ticketPojo);

}
