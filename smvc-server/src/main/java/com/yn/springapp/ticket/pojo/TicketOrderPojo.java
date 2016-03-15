package com.yn.springapp.ticket.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 车票订单
 * User: pei.xu
 * Date: 15-2-3
 * Time: 下午6:51
 */
public class TicketOrderPojo implements Serializable {

    private int id;
    private String bookId;
    private String orderNo;
    private BigDecimal money;
    private String status;

    public TicketOrderPojo() {
    }

    public TicketOrderPojo(int id, String bookId, String orderNo, BigDecimal money, String status) {
        this.id = id;
        this.bookId = bookId;
        this.orderNo = orderNo;
        this.money = money;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
