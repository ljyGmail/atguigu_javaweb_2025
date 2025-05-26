package com.atguigu.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo01Servlet
 * Package: com.atguigu.servlet
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 26. 오전 10:32
 * @Version 1.0
 */

@WebServlet(urlPatterns = {"/demo01"},
        initParams = {@WebInitParam(name = "hello", value = "world_annotation"),
                @WebInitParam(name = "uname", value = "jim")})
public class Demo01Servlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        String initValue = config.getInitParameter("hello");
        System.out.println("initValue: " + initValue);
        String initValue2 = config.getInitParameter("uname");
        System.out.println("initValue2: " + initValue2);

        ServletContext servletContext = getServletContext();
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");
        System.out.println("contextConfigLocation: " + contextConfigLocation);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request.getServletContext();
        // request.getSession().getServletContext();
    }
}

// Servlet生命周期: 实例化、初始化、服务、销毁
