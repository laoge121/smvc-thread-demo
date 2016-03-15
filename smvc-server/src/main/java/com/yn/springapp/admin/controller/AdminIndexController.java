package com.yn.springapp.admin.controller;

import com.yn.springapp.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pei.xu on 2015/10/26.
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController extends BaseController {

    @RequestMapping("index")
    public String index(Model model) {

        return "/admin/index";
    }

    @RequestMapping("home")
    public String home() {

        return "/admin/home";
    }
}
