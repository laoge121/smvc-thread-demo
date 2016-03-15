package com.yn.springapp.ticket.mvc;

import com.yn.springapp.common.BaseController;
import com.yn.springapp.ticket.service.TicketService;
import com.yn.springapp.util.queue.QueueExecuteThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * User: pei.xu
 * Date: 15-2-3
 * Time: 下午3:45
 */
@Controller
@RequestMapping("/queue")
public class QueueController extends BaseController {

    @Autowired
    private TicketService ticketService;

    @RequestMapping("/add.json")
    @ResponseBody
    public Map<String, Object> addData(final @RequestParam(value = "name", required = true) String name) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        QueueExecuteThread.addExecuteInfo(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("用户信息:" + name);
                return null;
            }
        });
        retMap.put("aa", "bbb");
        return retMap;
    }

    @RequestMapping("/query.json")
    @ResponseBody
    public Map<String, Object> queryTicketInfo(@RequestParam(value = "trainNo", required = true) String trainNo,
                                               @RequestParam(value = "departureTime", required = true) String departureTime) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("data", ticketService.queryTicket(trainNo, departureTime));
        return retMap;
    }

    @RequestMapping("/book.json")
    @ResponseBody
    public Map<String, Object> bookTicket(String passengers, String idCard, String trainNo, String startStation, String endStation, String departureTime) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        passengers = "";
        idCard = "";
        trainNo = "";
        startStation = "";
        endStation = "";
        departureTime = "2015-02-03";
        Boolean bool = ticketService.ticketQueueBook(passengers, idCard, trainNo, startStation, endStation, departureTime);
        retMap.put("ret", bool);
        return retMap;
    }
}
