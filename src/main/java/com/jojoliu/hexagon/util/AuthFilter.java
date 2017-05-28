package com.jojoliu.hexagon.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Jojo on 23/05/2017.
 */
public class AuthFilter implements javax.servlet.Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse resp=(HttpServletResponse) response;
        HttpSession session = req.getSession();
        if(session.isNew()){
            System.out.println("不是登录状态，跳转到登录页面!");
            resp.sendRedirect("/index.html");
        }else{
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
