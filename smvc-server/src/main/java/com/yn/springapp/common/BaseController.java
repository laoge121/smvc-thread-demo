package com.yn.springapp.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 基本的控制器
 * User: pei.xu
 * Date: 15-2-2
 * Time: 下午5:29
 */
public class BaseController {


    /**
     * 异常处理类
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @ExceptionHandler
    public ModelAndView executeExecuption(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("hello");

        return modelAndView;
    }
}
