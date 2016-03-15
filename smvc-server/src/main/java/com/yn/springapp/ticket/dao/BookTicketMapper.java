package com.yn.springapp.ticket.dao;

import com.yn.springapp.ticket.pojo.BookTicketPojo;
import org.springframework.stereotype.Repository;

/**
 * 车票预订接口
 * User: pei.xu
 * Date: 15-2-3
 * Time: 下午6:46
 */
public interface BookTicketMapper {

    /**
     * 查询指定的 车次信息
     *
     * @param ticketPojo
     * @return
     */
    public BookTicketPojo queryBookTicketByTrainNo(BookTicketPojo ticketPojo);

    /**
     * 保存车票可用信息
     *
     * @param ticketPojo
     */
    public void saveBookTicketInfo(BookTicketPojo ticketPojo);

}
