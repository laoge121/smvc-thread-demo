package com.yn.springapp.ticket.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 车票信息
 * User: pei.xu
 * Date: 15-2-3
 * Time: 下午6:47
 */
public class TicketPojo implements Serializable {

    private int id;
    private String trainNo;
    private String startStation;
    private String endStation;
    private String departureTime;
    private BigDecimal money;
    private int ticketNum;

    public TicketPojo() {
    }

    public TicketPojo(int id, String trainNo, String startStation, String endStation, String departureTime, BigDecimal money, int ticketNum) {
        this.id = id;
        this.trainNo = trainNo;
        this.startStation = startStation;
        this.endStation = endStation;
        this.departureTime = departureTime;
        this.money = money;
        this.ticketNum = ticketNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }
}
