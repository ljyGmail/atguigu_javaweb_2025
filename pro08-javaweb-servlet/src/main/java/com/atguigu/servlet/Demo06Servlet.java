package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo06Servlet
 * Package: com.atguigu.servlet
 * Description:
 * 演示服务器内部转发以及客户端重定向
 *
 * @Author ljy
 * @Create 2025. 5. 22. 오후 1:37
 * @Version 1.0
 */
public class Demo06Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Demo06...");
        // 服务器端内部转发
        // request.getRequestDispatcher("demo07").forward(request, response);
        // 客户端重定向
        response.sendRedirect("demo07");
    }
}
