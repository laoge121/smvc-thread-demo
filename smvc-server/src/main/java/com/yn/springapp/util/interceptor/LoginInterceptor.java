package com.yn.springapp.util.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.IOException;

/**
 * 监听请求上线文 和session的控制
 * User: pei.xu
 * Date: 15-2-2
 * Time: 下午5:32
 */
public class LoginInterceptor implements HttpSessionListener, ServletContextListener, Filter {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println(se.getSession().getId() + "session 创建!");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println(se.getSession().getId() + "session 注销!");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(sce.getServletContext().getServerInfo() + "上线文初始化!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println(sce.getServletContext().getServerInfo() + "上线文注销!");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(filterConfig.getFilterName() + "filter 初始化!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("doFilter 执行");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("fliter 注销 系统停止");
    }
}
