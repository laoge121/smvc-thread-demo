package com.yn.springapp.ticket.service;

import com.yn.springapp.ticket.dao.BookTicketMapper;
import com.yn.springapp.ticket.dao.TicketMapper;
import com.yn.springapp.ticket.dao.TicketOrderMapper;
import com.yn.springapp.ticket.pojo.BookTicketPojo;
import com.yn.springapp.ticket.pojo.TicketPojo;
import com.yn.springapp.util.interceptor.BeanUtil;
import com.yn.springapp.util.queue.QueueExecuteThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * 车票预订
 * User: pei.xu
 * Date: 15-2-3
 * Time: 下午6:06
 */
@Service("ticketService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = RuntimeException.class)
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private BookTicketMapper bookTicketMapper;

    @Autowired
    private TicketOrderMapper ticketOrderMapper;


    @Override
    public boolean ticketBook(String orderNo, String passengers, String idCard, String trainNo, String startStation, String endStation, String departureTime) {

        TicketPojo ticketPojo = new TicketPojo(0, trainNo, startStation, endStation, departureTime, null, 0);

        TicketPojo ticketPojo1 = ticketMapper.queryTicketByTrainNo(ticketPojo);

        if (ticketPojo1.getTicketNum() <= 0) {
            return false;
        }

        BookTicketPojo bookTicketPojo = new BookTicketPojo(0, orderNo, trainNo, passengers, idCard, ticketPojo1.getMoney());
        bookTicketMapper.saveBookTicketInfo(bookTicketPojo);

        //更新数量
        ticketPojo1.setTicketNum(ticketPojo1.getTicketNum() - 1);
        ticketMapper.updateTikcetInfo(ticketPojo1);

        return true;
    }

    @Override
    public boolean ticketQueueBook(final String passengers, final String idCard, final String trainNo, final String startStation, final String endStation, final String departureTime) {

        final String orderNo = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());

        //插入预订表
        QueueExecuteThread.addExecuteInfo(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                TicketService ticketService = (TicketService) BeanUtil.getBean("ticketService");
                boolean retbool = ticketService.ticketBook(orderNo, passengers, idCard, trainNo, startStation, endStation, departureTime);
                QueueExecuteThread.addResultInfo(orderNo, retbool);
                return retbool;
            }
        });
        Object obj = null;
        while (true) {
            obj = QueueExecuteThread.getResultInfo(orderNo);
            if (null == obj) {
                continue;
            } else {
                break;
            }
        }
        QueueExecuteThread.removeKey(orderNo);
        return (Boolean) obj;
    }

    @Override
    public TicketPojo queryTicket(String trainNo, String departureTime) {
        TicketPojo ticketPojo = new TicketPojo();
        ticketPojo.setTrainNo(trainNo);
        ticketPojo.setDepartureTime(departureTime);
        return ticketMapper.queryTicketByTrainNo(ticketPojo);
    }
}
