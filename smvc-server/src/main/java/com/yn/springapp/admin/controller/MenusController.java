package com.yn.springapp.admin.controller;

import com.yn.springapp.admin.service.MenusService;
import com.yn.springapp.common.BaseController;
import com.yn.springapp.util.result.ResultApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pei.xu on 2015/10/26.
 */
@Controller
@RequestMapping("/menus")
public class MenusController extends BaseController {

    @Autowired
    private MenusService menusService;

    @RequestMapping("/create")
    @ResponseBody
    public ResultApi queryMenus() {
        return ResultApi.success(menusService.queryTreeMenus());
    }
}