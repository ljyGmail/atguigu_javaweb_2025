package com.atguigu.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: Demo03Servlet
 * Package: com.atguigu.demo
 * Description:
 * 演示session保存作用域(demo03和demo04)
 *
 * @Author: ljy
 * @Create: 2025. 5. 22. 오후 11:53
 * @Version 1.0
 */
@WebServlet("/demo03")
public class Demo03Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 向session保存作用域保存数据
        request.getSession().setAttribute("uname", "lili_session");
        // 2. 客户端重定向
        response.sendRedirect("demo04");

        // 3. 服务器端转发
        // request.getRequestDispatcher("/demo04").forward(request, response);
    }
}
