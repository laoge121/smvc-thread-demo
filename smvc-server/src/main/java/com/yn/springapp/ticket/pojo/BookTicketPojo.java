package com.yn.springapp.ticket.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 车票预订信息
 * User: pei.xu
 * Date: 15-2-3
 * Time: 下午6:49
 */
public class BookTicketPojo implements Serializable {

    private int id;
    private String orderNo;
    private String trainNo;
    private String passengers;
    private String idCard;
    private BigDecimal money;

    public BookTicketPojo() {
    }

    public BookTicketPojo(int id, String orderNo, String trainNo, String passengers, String idCard, BigDecimal monery) {
        this.id = id;
        this.orderNo = orderNo;
        this.trainNo = trainNo;
        this.passengers = passengers;
        this.idCard = idCard;
        this.money = monery;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
