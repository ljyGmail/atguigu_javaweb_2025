package com.atguigu.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * ClassName: Demo01Filter
 * Package: com.atguigu.filter
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 26. 오후 3:30
 * @Version 1.0
 */
// @WebFilter("/demo01.do")
//@WebFilter("*.do")
public class Demo01Filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("helloA");
        // 放行
        chain.doFilter(request, response);
        System.out.println("helloA2");
    }

    @Override
    public void destroy() {

    }
}
