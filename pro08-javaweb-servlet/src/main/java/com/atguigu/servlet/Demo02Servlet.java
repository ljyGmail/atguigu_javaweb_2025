package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo02Servlet
 * Package: com.atguigu.servlet
 * Description:
 * 演示Servlet的生命周期
 *
 * @Author: ljy
 * @Create: 2025. 5. 21. 오후 4:48
 * @Version 1.0
 */
public class Demo02Servlet extends HttpServlet {

    public Demo02Servlet() {
        System.out.println("正在实例化...");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("正在初始化...");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("正在服务...");
    }

    @Override
    public void destroy() {
        System.out.println("正在销毁...");
    }
}
