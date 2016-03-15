package com.yn.springapp.ticket.dao;

import com.yn.springapp.ticket.pojo.TicketPojo;
import org.springframework.stereotype.Repository;

/**
 * 车票接口
 * User: pei.xu
 * Date: 15-2-3
 * Time: 下午6:46
 */
public interface TicketMapper {

    /**
     * 查询指定的 车次信息
     *
     * @param ticketPojo
     * @return
     */
    public TicketPojo queryTicketByTrainNo(TicketPojo ticketPojo);

    /**
     * 保存车票可用信息
     *
     * @param ticketPojo
     */
    public void saveTicketInfo(TicketPojo ticketPojo);

    /**
     * 更新指定车票的剩余票数
     *
     * @param ticketPojo
     */
    public void updateTikcetInfo(TicketPojo ticketPojo);

}
