package com.atguigu.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo06Servlet
 * Package: com.atguigu.demo
 * Description:
 *
 * @Author: ljy
 * @Create: 2025. 5. 22. 오후 11:55
 * @Version 1.0
 */
@WebServlet("/demo06")
public class Demo06Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获取application保存作用域保存的数据，key为uname
        ServletContext application = request.getServletContext();
        Object unameObj = application.getAttribute("uname");
        System.out.println(unameObj);
    }
}
