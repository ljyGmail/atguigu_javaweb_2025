package com.atguigu.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * ClassName: Filter03
 * Package: com.atguigu.filter
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 26. 오후 3:52
 * @Version 1.0
 */
//@WebFilter("*.do")
public class Filter03 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("C");
        // 放行
        chain.doFilter(request, response);
        System.out.println("C2");
    }

    @Override
    public void destroy() {

    }
}
