package com.yn.springapp.shop.controller;

import com.yn.springapp.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 商城首页
 * User: pei.xu
 * Date: 15-4-21
 * Time: 上午10:28
 */
@Controller
@RequestMapping("/index")
public class ShopIndexController extends BaseController {

    @RequestMapping("head.html")
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");

        Map<String, Object> tmpMap = new HashMap<String, Object>();
        tmpMap.put("aa", "bb");

        return "index";
    }

}
