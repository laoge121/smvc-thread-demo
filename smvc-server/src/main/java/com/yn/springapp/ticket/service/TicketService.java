package com.yn.springapp.ticket.service;

import com.yn.springapp.ticket.pojo.TicketPojo;

/**
 * 票务模块
 * User: pei.xu
 * Date: 15-2-3
 * Time: 下午6:03
 */
public interface TicketService {

    /**
     * 车票预订接口
     *
     * @param passengers
     * @param idCard
     * @param trainNo
     * @param startStation
     * @param endStation
     * @param departureTime
     * @return
     */
    public boolean ticketBook(String orderNo, String passengers, String idCard, String trainNo, String startStation, String endStation, String departureTime);

    /**
     * 预订车票排队
     *
     * @param passengers
     * @param idCard
     * @param trainNo
     * @param startStation
     * @param endStation
     * @param departureTime
     * @return
     */
    public boolean ticketQueueBook(String passengers, String idCard, String trainNo, String startStation, String endStation, String departureTime);

    /**
     * 查询票信息
     *
     * @param trainNo
     * @param departureTime
     * @return
     */
    public TicketPojo queryTicket(String trainNo, String departureTime);
}
